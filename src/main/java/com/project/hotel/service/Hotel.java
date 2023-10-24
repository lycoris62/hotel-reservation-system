package com.project.hotel.service;

import java.time.LocalDateTime;
import java.util.List;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Reservation;

public class Hotel implements HotelService {
    @Override
    public String  reserve(Person person, LocalDateTime dateTime) {
        return "fail";
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
