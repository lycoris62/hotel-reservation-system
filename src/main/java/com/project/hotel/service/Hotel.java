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
		LocalDateTime reservationDateTime = request.dateTime();
		LocalDate reservationDate = reservationDateTime.toLocalDate();

		if (!user.getRole().equals(Role.CUSTOMER)) {
			return new ReserveResponse(null, true);
		}

		List<Room> availableRoomList = getAvailableRoomList(user, reservationDate);

		if (availableRoomList.isEmpty()) {
			return new ReserveResponse(null, true);
		}

		Reservation reservation = processReservation(availableRoomList, user, reservationDateTime);

		return new ReserveResponse(reservation, false);
	}

	private List<Room> getAvailableRoomList(Person user, LocalDate reservationDate) {
		return roomRepository.findRoomMoney(user.getMoney())
			.stream()
			.filter(room -> !room.isReserved(reservationDate))
			.toList();
	}

	private Reservation processReservation(List<Room> availableRoomList, Person user,
		LocalDateTime reservationDateTime) {
		Room room = availableRoomList.get(0);
		payForRoomFee(user, room);
		room.reserve(reservationDateTime.toLocalDate());

		return new Reservation(room, user, reservationDateTime, room.getFee());
	}

	private void payForRoomFee(Person user, Room room) {
		user.minusMoney(room.getFee());
		assetRepository.plus(room.getFee());
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
        return null;
    }

}
