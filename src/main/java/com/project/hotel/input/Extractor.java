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

        if (command.equals("1")) {
            System.out.println("객실 이름을 입력해세요.");
            var roomId = input.command();
            System.out.println("예약 년도를 입력하세요");
            var year = Integer.parseInt(input.command());
            System.out.println("예약 월을 입력하세요");
            var month = Integer.parseInt(input.command());
            System.out.println("예약 일을 일력하세요");
            var day = Integer.parseInt(input.command());
            System.out.println("고객 id를 입력하세요");
            var id = input.command();
            System.out.println("고객 비밀번호를 입력하세요");
            var password = input.command();
            var time = LocalDateTime.of(year, month, day, 0, 0, 0);
            return new ReserveRequest(new Person(id, password, "", null, null, 0), roomId, time);
        } else if (command.equals("2")) {
            System.out.println("2. 예약 조회");
            System.out.println("아이디 입력 : ");
            var userId = input.command();
            System.out.println("비밀번호 입력 : ");
            var password = input.command();
            return new GetReserveListRequest(new Person(userId, password, null, null, null, 0));
        } else if (command.equals("3")) {
            var reservationId = input.command();
            return new DeleteReserveRequest(null, reservationId);
        }
        throw new RuntimeException("입력 오류");
    }
}
