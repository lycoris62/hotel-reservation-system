package com.project.hotel.output;

import com.project.hotel.output.response.DeleteReservationResponse;
import com.project.hotel.output.response.GetReservationResponse;
import com.project.hotel.output.response.ReserveResponse;

import java.time.format.DateTimeFormatter;

public class ViewResolver {

    public String getPage(Object response) {
        if (response instanceof GetReservationResponse getReservationResponse) {

            var result = new StringBuilder("예약 정보\n");

            result.append(String.format("%-10s %-10s %-20s %-10s\n", "예약일", "예약번호", "룸", "가격"));

            getReservationResponse.reservationList()
                    .forEach(reservation -> {
                        result.append(String.format("%-10s %-10s %-20s %-10d\n",
                                reservation.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                reservation.getId(),
                                reservation.getRoom().getName(),
                                reservation.getPrice()
                        ));
                    });

            return result.toString();
        }

        if (response instanceof DeleteReservationResponse deleteReservationResponse) {
            if (deleteReservationResponse.isDeleted()) {
                return """
                        예약이 삭제되었습니다.
                        """;
            }

            return """
                    예약 삭제가 실패했습니다.
                    """;
        }

        if (response instanceof ReserveResponse reserveResponse) {

            if(reserveResponse.isError()){
                return "예약에 실패했습니다.";
            }

            var reservation = reserveResponse.reservation();
            var reservationInfo = String.format("%-10s %-10s %-10s %-10d",
                    reservation.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    reservation.getId(),
                    reservation.getRoom().getName(),
                    reservation.getPrice());

            return
                    "예약되었습니다.\n" +
                            String.format("%-10s %-10s %-10s %-10s\n", "예약일", "예약번호", "룸", "가격") +
                            reservationInfo;
        }

        return "올바르지 않은 응답입니다.";
    }
}
