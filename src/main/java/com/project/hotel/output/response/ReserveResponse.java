package com.project.hotel.output.response;

import com.project.hotel.domain.Reservation;

public record ReserveResponse(
        Reservation reservation,
		boolean isError
) {
}
