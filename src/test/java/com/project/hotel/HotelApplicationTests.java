package com.project.hotel;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("통합테스트")
class HotelApplicationTests {

    private static HotelApplication app = new HotelApplication();

    @DisplayName("예약 하기 기능 : 성공")
    @Test
    void test1() {
        // given
        // 유저 저장
        Person user = new Person("user1", "111", "", "", Role.CUSTOMER, 10000);
        app.sessionManager.save(user);
        // 객실 저장
        Room room = new Room(1000, new HashSet<>(), "test", 200.0, 1000);
        app.roomRepository.save(room);

        ReserveRequest request = new ReserveRequest(user, "test", LocalDateTime.of(2023, 10, 2, 0, 0));
        // when
        ReserveResponse response = (ReserveResponse) app.dispatcher().handle(request);
        // then
        assertThat(response.isError()).isFalse();
        assertThat(response.reservation())
                .extracting("room.name", "person.id", "dateTime", "price")
                .containsExactly("test", "user1", LocalDateTime.of(2023, 10, 2, 0, 0), 1000);
    }

    @DisplayName("예약 하기 기능 : 실패 (이미 예약됨)")
    @Test
    void reserveFailBecauseOtherUserAlreadyReserved() {
        // given
        // 유저 저장
        Person user = new Person("user1", "111", "", "", Role.CUSTOMER, 10000);
        app.sessionManager.save(user);
        // 객실 저장
        Room room = new Room(1000, new HashSet<>(), "test", 200.0, 1000);
        app.roomRepository.save(room);
        // 예약 저장
        Reservation reservation = new Reservation(room, user, LocalDateTime.of(2023, 1, 1, 0, 0), 1000);
        room.reserve(reservation.getDate().toLocalDate());
        app.reservationRepository.save(reservation);

        ReserveRequest request = new ReserveRequest(user, "test", LocalDateTime.of(2023, 1, 1, 0, 0));
        // when
        ReserveResponse response = (ReserveResponse) app.dispatcher().handle(request);
        // then
        assertThat(response.isError()).isTrue();
    }

    @DisplayName("예약 하기 기능 : 실패 (유저가 돈이 없음)")
    @Test
    void test() {
        // given
        // 유저 저장
        Person user = new Person("user1", "111", "", "", Role.CUSTOMER, 0);
        app.sessionManager.save(user);
        // 객실 저장
        Room room = new Room(1000, new HashSet<>(), "test", 200.0, 1000);
        app.roomRepository.save(room);

        ReserveRequest request = new ReserveRequest(user, "test", LocalDateTime.of(2023, 11, 2, 0, 0));
        // when
        ReserveResponse response = (ReserveResponse) app.dispatcher().handle(request);
        // then
        assertThat(response.isError()).isTrue();
    }

    @DisplayName("예약 취소 기능 : 성공")
    @Test
    void test2() {
        // given
        // 유저 등록
        Person user = new Person("user1", "111", "", "", Role.CUSTOMER, 0);
        app.sessionManager.save(user);

        // 예약 등록
        Room room = app.roomRepository.findRoomList(LocalDate.of(2000, 12, 12)).stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("2000-12-12에 예약할 수 있는 방이 없어서 실패"));

        Reservation reservation = new Reservation(room, user, LocalDateTime.of(2023, 1, 1, 0, 0), room.getFee());
        app.reservationRepository.save(reservation);

        // 삭제 요청
        DeleteReserveRequest request = new DeleteReserveRequest(user, reservation.getId());
        // when
        DeleteReservationResponse response = (DeleteReservationResponse) app.dispatcher().handle(request);
        // then
        assertThat(response.isDeleted()).isTrue();
        assertThat(user.getMoney()).isEqualTo(room.getFee());
    }

    @DisplayName("예약 취소 기능 : 실패 (다른 사람이 취소할 때)")
    @Test
    void test2Fail() {
        // given
        // 유저 등록
        Person user = new Person("user1", "111", "", "", Role.CUSTOMER, 0);
        app.sessionManager.save(user);

        // 예약 등록
        Room room = app.roomRepository.findRoomList(LocalDate.of(2000, 12, 12)).stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("2000-12-12에 예약할 수 있는 방이 없어서 실패"));

        Reservation reservation = new Reservation(room, user, LocalDateTime.of(2023, 1, 1, 0, 0), 1000);
        app.reservationRepository.save(reservation);

        // 삭제 요청
        Person other = new Person("hacker", "234", "", "", Role.CUSTOMER, 0);
        app.sessionManager.save(other);
        DeleteReserveRequest request = new DeleteReserveRequest(other, reservation.getId());
        // when
        DeleteReservationResponse response = (DeleteReservationResponse) app.dispatcher().handle(request);
        // then
        assertThat(response.isDeleted()).isFalse();
    }

    @DisplayName("예약 목록 조회 : 성공")
    @Test
    void test3() {
        // given
        // 유저 등록
        var user = new Person("getReserveUser", "111", "", "", Role.CUSTOMER, 0);
        app.sessionManager.save(user);

        // 예약 등록
        var room = app.roomRepository.findRoomList(LocalDate.of(2000, 3, 12)).stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("2000-03-12에 예약할 수 있는 방이 없어서 실패"));

        var reservation = new Reservation(room, user, LocalDateTime.of(2023, 1, 1, 0, 0), room.getFee());
        app.reservationRepository.save(reservation);

        GetReserveListRequest request = new GetReserveListRequest(user);
        // when
        GetReservationResponse response = (GetReservationResponse) app.dispatcher().handle(request);
        // then
        assertThat(response.reservationList()).contains(reservation);
    }

}
