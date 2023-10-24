package com.project.hotel.input.request;

import com.project.hotel.domain.Person;

public record DeleteReserveRequest(
	Person person,
	String reservationId
) {
}
