package com.project.hotel.repository;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationRepositoryImpl implements ReservationRepository{

    private final Map<String, Reservation> reservationStorage = new HashMap<>();

    @Override
    public List<Reservation> findAll() {
        List<Reservation> list = new ArrayList<>();
        for (Reservation reservation : reservationStorage.values()) {
            list.add(reservation);
        }
        return list;
    }

    @Override
    public List<Reservation> findUserReservation(Person user) {
        List<Reservation> list = new ArrayList<>();
        // 입력받은 매개변수 user로 Reservation를 조회한다.
        // 그러나 Reservation은 person을 가지고있다.
        // reservationStorage<<안에 들어있는 person과 비교해서 입력받은 user와 같은 것을 찾아라.
        for (Reservation reservation : reservationStorage.values()) {
            if (user.equals(reservation.getPerson())) {
                list.add(reservation);
            }
        }
        return list;
    }

    @Override
    public void delete(String userId) {
        reservationStorage.remove(userId);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationStorage.put(reservation.getId(), reservation);
    }
}
