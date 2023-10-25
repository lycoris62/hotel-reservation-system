package com.project.hotel.service;

import java.util.List;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Reservation;
import com.project.hotel.domain.Role;
import com.project.hotel.input.request.DeleteReserveRequest;
import com.project.hotel.input.request.GetReserveListRequest;
import com.project.hotel.input.request.ReserveRequest;
import com.project.hotel.output.response.DeleteReservationResponse;
import com.project.hotel.output.response.GetReservationResponse;
import com.project.hotel.output.response.ReserveResponse;
import com.project.hotel.repository.ReservationRepository;

public class Hotel implements HotelService {

    private ReservationRepository reservationRepository;

    @Override
    public ReserveResponse reserve(ReserveRequest request) {
        return null;
    }

    @Override
    public GetReservationResponse getReservations(GetReserveListRequest request) {

        List<Reservation> reservationList = getReservationList(request);
        return new GetReservationResponse(reservationList);
    }

    private List<Reservation> getReservationList(GetReserveListRequest request) {
        Person person = request.person();
        Role role = person.getRole();

        return requestReservationList(role, person);
    }

    private List<Reservation> requestReservationList(Role role, Person person) {
        return switch (role) {
            case ADMIN -> reservationRepository.findAll();
            case CUSTOMER -> reservationRepository.findUserReservation(person);
        };
    }

    @Override
    public DeleteReservationResponse cancelReservation(DeleteReserveRequest request) {
        return null;
    }

}
