package com.project.hotel.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Reservation;
import com.project.hotel.domain.Role;
import com.project.hotel.domain.Room;
import com.project.hotel.input.request.DeleteReserveRequest;
import com.project.hotel.input.request.GetReserveListRequest;
import com.project.hotel.input.request.ReserveRequest;
import com.project.hotel.output.response.DeleteReservationResponse;
import com.project.hotel.output.response.GetReservationResponse;
import com.project.hotel.output.response.ReserveResponse;
import com.project.hotel.repository.AssetDb;
import com.project.hotel.repository.AssetRepository;
import com.project.hotel.repository.ReservationRepository;
import com.project.hotel.repository.ReservationRepositoryImpl;
import com.project.hotel.repository.RoomDb;
import com.project.hotel.repository.RoomRepository;

public class Hotel implements HotelService {

	private final ReservationRepository reservationRepository = new ReservationRepositoryImpl();
	private final RoomRepository roomRepository = new RoomDb();
	private final AssetRepository assetRepository = new AssetDb();

	@Override
	public ReserveResponse reserve(ReserveRequest request) {
		Person user = request.person();

		if (!user.getRole().equals(Role.CUSTOMER)) {
			System.out.println("고객이 아님");
			return new ReserveResponse(null, true);
		}

		List<Room> availableRoomList = getAvailableRoomList(request);

		if (availableRoomList.isEmpty()) {
			System.out.println("없는 객실");
			return new ReserveResponse(null, true);
		}

		Reservation reservation = processReservation(availableRoomList, request);

		return new ReserveResponse(reservation, false);
	}

	private List<Room> getAvailableRoomList(ReserveRequest request) {
		Person user = request.person();
		String roomName = request.roomName();
		LocalDate reservationDate = request.dateTime().toLocalDate();

		return roomRepository.findRoom(roomName)
			.stream()
			.filter(room -> isAvailable(room, reservationDate, user))
			.toList();
	}

	private boolean isAvailable(Room room, LocalDate reservationDate, Person user) {
		return !room.isReserved(reservationDate) && room.getFee() <= user.getMoney();
	}

	private Reservation processReservation(List<Room> availableRoomList, ReserveRequest request) {
		Person user = request.person();
		LocalDateTime reservationDateTime = request.dateTime();

		Room room = availableRoomList.get(0);
		payForRoomFee(user, room);

		return saveReservation(room, reservationDateTime, user);
	}

	private void payForRoomFee(Person user, Room room) {
		user.minusMoney(room.getFee());
		assetRepository.plus(room.getFee());
	}

	private Reservation saveReservation(Room room, LocalDateTime reservationDateTime, Person user) {
		room.reserve(reservationDateTime.toLocalDate());
		Reservation reservation = new Reservation(room, user, reservationDateTime, room.getFee());
		reservationRepository.save(reservation);
		return reservation;
	}

    @Override
    public GetReservationResponse getReservations(GetReserveListRequest request) {

        List<Reservation> reservationList = getReservationList(request);
        return new GetReservationResponse(reservationList);
    }

    private List<Reservation> getReservationList(GetReserveListRequest request) {
        Person person = request.person();
        Role role = person.getRole();

        return requestReservationList(role, person);
    }

    private List<Reservation> requestReservationList(Role role, Person person) {
        return switch (role) {
            case ADMIN -> reservationRepository.findAll();
            case CUSTOMER -> reservationRepository.findUserReservation(person);
        };
    }

	@Override
	public DeleteReservationResponse cancelReservation(DeleteReserveRequest request) {
		Person user = request.person();
		String reservationId = request.reservationId();

		if (!user.getRole().equals(Role.CUSTOMER)) {
			return new DeleteReservationResponse(false);
		}

		Optional<Reservation> optionalReservation = getReservation(user, reservationId);

		if (optionalReservation.isEmpty()) {
			return new DeleteReservationResponse(false);
		}

		processReservationCancel(optionalReservation.get());

		return new DeleteReservationResponse(true);
	}

	private Optional<Reservation> getReservation(Person user, String reservationId) {
		return reservationRepository.findUserReservation(user)
			.stream()
			.filter(reservation -> reservation.getId().equals(reservationId))
			.findAny();
	}

	private void processReservationCancel(Reservation reservation) {
		refundRoomFee(reservation);
		cancelReservation(reservation);
	}

	private void refundRoomFee(Reservation reservation) {
		Person user = reservation.getPerson();
		Room room = reservation.getRoom();

		user.plusMoney(room.getFee());
		assetRepository.minus(room.getFee());
	}

	private void cancelReservation(Reservation reservation) {
		Room room = reservation.getRoom();
		LocalDate reservationDate = reservation.getDate().toLocalDate();
		String reservationId = reservation.getId();

		room.cancelReserve(reservationDate);
		reservationRepository.delete(reservationId);
	}
}
