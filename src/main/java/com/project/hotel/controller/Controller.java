package com.project.hotel.controller;

import com.project.hotel.common.SessionManager;
import com.project.hotel.output.response.DeleteReservationResponse;
import com.project.hotel.output.response.GetReservationResponse;
import com.project.hotel.output.response.ReserveResponse;
import com.project.hotel.service.HotelService;

public class Controller {

    private final HotelService hotel;
    private final SessionManager sessionManager;

    public Controller(HotelService hotel, SessionManager sessionManager) {
        this.hotel = hotel;
        this.sessionManager = sessionManager;
    }

    public ReserveResponse reserve(String roomId, String userId, String password) {
        return null;
    }

    public DeleteReservationResponse deleteReservation(String reservationId, String userId, String password){
        return null;
    }

    public GetReservationResponse getReservationList(String userId, String password){
        return null;
    }
}
