package com.project.hotel.service;

import com.project.hotel.input.request.DeleteReserveRequest;
import com.project.hotel.input.request.GetReserveListRequest;
import com.project.hotel.input.request.ReserveRequest;
import com.project.hotel.output.response.DeleteReservationResponse;
import com.project.hotel.output.response.GetReservationResponse;
import com.project.hotel.output.response.ReserveResponse;

public interface HotelService {

	ReserveResponse reserve(ReserveRequest request);

	GetReservationResponse getReservations(GetReserveListRequest request);

	DeleteReservationResponse cancelReservation(DeleteReserveRequest request);
}
