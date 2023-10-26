package com.project.hotel.controller;

import com.project.hotel.common.SessionManager;
import com.project.hotel.domain.Reservation;
import com.project.hotel.input.request.DeleteReserveRequest;
import com.project.hotel.input.request.GetReserveListRequest;
import com.project.hotel.input.request.ReserveRequest;
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
        var user = sessionManager.getUser(userId, password);
        var localDateTime = new Reservation().getDate();
        var request = new ReserveRequest(user, roomId, localDateTime);
        return hotel.reserve(request);
    }
    public GetReservationResponse getReservationList(String userId, String password) {
        var user = sessionManager.getUser(userId,password);
        var requset = new GetReserveListRequest(user);
        return hotel.getReservations(requset);
    }
    public DeleteReservationResponse deleteReservation(String reservationId, String userId, String password) {
        var user = sessionManager.getUser(userId, password);
        var request = new DeleteReserveRequest(user, reservationId);
        return hotel.cancelReservation(request);
    }


}
