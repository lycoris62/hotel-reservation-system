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
        System.out.println("1 예약하기 2 예약조회 3. 예약 삭제");
        String command = input.command();

        Person tempUser = login();

        if (command.equals("1")) {

            System.out.println("객실 이름을 입력해세요.");
            var roomId = input.command();
            System.out.println("예약 년도를 입력하세요");
            var year = Integer.parseInt(input.command());
            System.out.println("예약 월을 입력하세요");
            var month = Integer.parseInt(input.command());
            System.out.println("예약 일을 일력하세요");
            var day = Integer.parseInt(input.command());

            var time = LocalDateTime.of(year, month, day, 0, 0, 0);
            return new ReserveRequest(tempUser, roomId, time);
        } else if (command.equals("2")) {

            return new GetReserveListRequest(tempUser);
        } else if (command.equals("3")) {

            System.out.println("예약 취소할 예약 번호를 입력하세요");
            var reservationId = input.command();

            return new DeleteReserveRequest(tempUser, reservationId);
        }
        throw new RuntimeException("입력 오류");
    }

    private Person login() throws IOException {
        System.out.println("고객 id를 입력하세요");
        var id = input.command();
        System.out.println("고객 비밀번호를 입력하세요");
        var password = input.command();

        return new Person(id, password);
    }
}