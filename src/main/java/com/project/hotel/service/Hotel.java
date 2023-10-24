package com.project.hotel.service;

import java.util.List;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Reservation;

public class Hotel implements HotelService {
    @Override
    public boolean reserve(Person person) {
        return false;
    }

    @Override
    public List<Reservation> getReservations(Person person) {
        return null;
    }

    @Override
    public boolean cancelReservation(Person person, String reservationId) {
        return false;
    }
}
