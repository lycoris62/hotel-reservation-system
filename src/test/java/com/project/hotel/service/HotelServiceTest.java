package com.project.hotel.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Reservation;
import com.project.hotel.domain.Role;
import com.project.hotel.domain.Room;
import com.project.hotel.input.request.GetReserveListRequest;
import com.project.hotel.output.response.GetReservationResponse;
import com.project.hotel.repository.AssetDb;
import com.project.hotel.repository.AssetRepository;
import com.project.hotel.repository.ReservationRepository;
import com.project.hotel.repository.ReservationRepositoryImpl;
import com.project.hotel.repository.RoomDb;
import com.project.hotel.repository.RoomRepository;

@DisplayName("호텔 서비스 테스트")
class HotelServiceTest {

	private HotelService hotelService;
	private ReservationRepository reservationRepository;
	private RoomRepository roomRepository;
	private AssetRepository assetRepository;

	@BeforeEach
	void setUp() {
		reservationRepository = new ReservationRepositoryImpl();
		roomRepository = new RoomDb();
		assetRepository = new AssetDb();
		hotelService = new Hotel(reservationRepository, roomRepository, assetRepository);
	}

	/*
	- 예약 조회 -
	1. 고객이 예약 조회 시 고객만 조회
	2. 관리자가 예약 조회 시 모두 조회
	 */

	@DisplayName("고객이 예약 조회 시 고객의 예약만 조회")
	@Test
	void getReservationsByUser() {

		// given (상황)
		Room room = new Room(1, new HashSet<>(), "single room", 33.0, 10000);

		Person user1 = new Person("user1", "111", "010-0000-0000", "홍길동", Role.CUSTOMER, 20000);
		Person user2 = new Person("user2", "222", "010-0000-1111", "이순신", Role.CUSTOMER, 15000);

		Reservation user1Reservation1 = new Reservation(room, user1, LocalDateTime.of(2023,10,12, 0, 0, 0), 10000);
		Reservation user1Reservation2 = new Reservation(room, user1, LocalDateTime.of(2023,10,13,0,0,0) , 10000);
		Reservation user2Reservation1 = new Reservation(room, user2, LocalDateTime.of(2023,10,14,0,0,0), 10000);

		reservationRepository.save(user1Reservation1);
		reservationRepository.save(user1Reservation2);
		reservationRepository.save(user2Reservation1);

		GetReserveListRequest user1Request = new GetReserveListRequest(user1);
		GetReserveListRequest user2Request = new GetReserveListRequest(user2);

		// when (실행)
		GetReservationResponse user1Response = hotelService.getReservations(user1Request);
		GetReservationResponse user2Response = hotelService.getReservations(user2Request);

		// then (결과)
		assertThat(user1Response.reservationList().size()).isEqualTo(2);
		assertThat(user2Response.reservationList().size()).isEqualTo(1);
	}

	@DisplayName("관리자가 예약 조회 시 모든 예약 조회")
	@Test
	void getAllReservations() {

		// given (상황)
		Room room = new Room(1, new HashSet<>(), "single room", 33.0, 10000);

		Person admin = new Person("admin", "123", "010-1111-1111", "관리자", Role.ADMIN, 30000);
		Person user1 = new Person("user1", "111", "010-0000-0000", "홍길동", Role.CUSTOMER, 20000);
		Person user2 = new Person("user2", "222", "010-0000-1111", "이순신", Role.CUSTOMER, 15000);

		Reservation user1Reservation1 = new Reservation(room, user1, LocalDateTime.of(2023,10,12, 0, 0, 0), 10000);
		Reservation user1Reservation2 = new Reservation(room, user1, LocalDateTime.of(2023,10,13,0,0,0) , 10000);
		Reservation user2Reservation1 = new Reservation(room, user2, LocalDateTime.of(2023,10,14,0,0,0), 10000);

		reservationRepository.save(user1Reservation1);
		reservationRepository.save(user1Reservation2);
		reservationRepository.save(user2Reservation1);

		GetReserveListRequest request = new GetReserveListRequest(admin);

		// when (실행)
		GetReservationResponse response = hotelService.getReservations(request);

		// then (결과)
		assertThat(response.reservationList().size()).isEqualTo(3);
	}
}