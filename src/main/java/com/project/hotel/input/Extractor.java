package com.project.hotel.input;

import com.project.hotel.domain.Person;
import com.project.hotel.input.request.DeleteReserveRequest;
import com.project.hotel.input.request.GetReserveListRequest;
import com.project.hotel.input.request.ReserveRequest;

import java.io.IOException;
import java.time.LocalDateTime;

public class Extractor {

    private Input input;

    public Extractor(Input input) {
        this.input = input;
    }

    public Object getRequest() throws IOException {
        // 1 예약하기 2 예약조회 3. 예약 삭제
        String command = input.command();

        if (command.equals("1")) {
            var roomId = input.command();
            var year = Integer.parseInt(input.command());
            var month = Integer.parseInt(input.command());
            var day = Integer.parseInt(input.command());
            var time = LocalDateTime.of(year, month, day, 0, 0, 0);
            return new ReserveRequest(null, roomId, time);
        } else if (command.equals("2")) {
            var userId = input.command();
            var password = input.command();
            return new GetReserveListRequest(new Person(userId, password, null, null, null, 0));
        } else if (command.equals("3")) {
            var reservationId = input.command();
            return new DeleteReserveRequest(null, reservationId);
        }
        throw new RuntimeException("입력 오류");
    }
}
