package com.project.hotel.input.request;

import com.project.hotel.domain.Person;

public record GetReserveListRequest(
	Person person
) {
}
