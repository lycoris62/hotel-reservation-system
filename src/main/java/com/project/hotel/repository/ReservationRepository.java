package com.project.hotel.repository;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> findAll();
    // 7. 고객은 자신의 예약 목록을 조회할 수 있다. 6. 호텔은 모든 예약 목록을 조회할 수 있다.
    List<Reservation> findUserReservation(Person user);
    // 8. 고객은 자신의 예약을 취소할 수 있다.
    void delete(String id);
    Reservation save(Reservation reservation);
}