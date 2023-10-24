package com.project.hotel.output.response;

import com.project.hotel.domain.Reservation;

import java.util.List;

public record GetReservationResponse(
        List<Reservation> reservationList
) {
}
