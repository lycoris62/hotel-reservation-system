package com.project.hotel.service;

import com.project.hotel.input.request.DeleteReserveRequest;
import com.project.hotel.input.request.GetReserveListRequest;
import com.project.hotel.input.request.ReserveRequest;
import com.project.hotel.output.response.DeleteReservationResponse;
import com.project.hotel.output.response.GetReservationResponse;
import com.project.hotel.output.response.ReserveResponse;

public class Hotel implements HotelService {

    @Override
    public ReserveResponse reserve(ReserveRequest request) {
        return null;
    }

    @Override
    public GetReservationResponse getReservations(GetReserveListRequest request) {
        return null;
    }

    @Override
    public DeleteReservationResponse cancelReservation(DeleteReserveRequest request) {
        return null;
    }

}
