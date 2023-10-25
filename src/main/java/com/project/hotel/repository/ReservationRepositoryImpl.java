package com.project.hotel.repository;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationRepositoryImpl implements ReservationRepository{

    private final Map<String, Reservation> reservationStorage = new HashMap<>();

    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public List<Reservation> findUserReservation(Person user) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationStorage.put(reservation.getId(), reservation);
    }
}
