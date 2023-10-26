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
        int index = 0;
        for (String userId : reservationStorage.keySet()) {
            list.add(index, reservationStorage.get(userId));
            index++;
        }
        return list;
    }

    @Override
    public List<Reservation> findUserReservation(Person user) {
        List<Reservation> list = new ArrayList<>();
        int index = 0;
        for (String userId : reservationStorage.keySet()) {
            if (user.getId().equals(userId)) {
                list.add(index, reservationStorage.get(userId));
                index++;
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
