package com.project.hotel.input.request;

import java.time.LocalDateTime;

import com.project.hotel.domain.Person;

public record ReserveRequest(
	Person person,
	String roomId,
	LocalDateTime dateTime
) {
}
