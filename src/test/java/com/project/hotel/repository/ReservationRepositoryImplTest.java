package com.project.hotel.repository;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Reservation;
import com.project.hotel.domain.Role;
import com.project.hotel.domain.Room;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReservationRepositoryImplTest {

    @Test
    void findAll() {
        // given
        var reservationRepository = new ReservationRepositoryImpl();
        // when
        List<Reservation> reservationList = reservationRepository.findAll();
        // then
        assertThat(reservationList.size())
                .isEqualTo(0);

        Assertions.assertEquals(reservationList.size(), 0);
    }

    @Test
    void findAllWhenOneReservation() {
        // given
        var reservationRepository = new ReservationRepositoryImpl();
        Room room = new Room(1, new HashSet<>(), "room", 100.0, 1000);
        reservationRepository.save(new Reservation(
                room,
                new Person("1", "111", "", "홍길동", Role.CUSTOMER, 1000),
                LocalDateTime.now(),
                1000)
        );
        // when
        List<Reservation> reservationList = reservationRepository.findAll();
        // then
        assertThat(reservationList.size()).isEqualTo(1);
        assertThat(reservationList)
                .extracting("price", "room")
                .contains(
                        Tuple.tuple(1000, room)
                );
    }

    @Test
    void test() {
        // given
        ReservationRepository mock = Mockito.mock(ReservationRepository.class);

        Room room = new Room(1, new HashSet<>(), "room", 100.0, 1000);

        var reservation = new Reservation(
                room,
                new Person("1", "111", "", "홍길동", Role.CUSTOMER, 1000),
                LocalDateTime.now(),
                1000);

        BDDMockito.given(mock.findAll())
                .willReturn(
                List.of(reservation)
        );
        // when
        List<Reservation> reservationList = mock.findAll();
        // then
        System.out.println(reservationList);
    }
}