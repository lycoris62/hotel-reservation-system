package com.project.hotel;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.hotel.common.SessionManager;
import com.project.hotel.controller.Controller;
import com.project.hotel.controller.Dispatcher;
import com.project.hotel.input.Extractor;
import com.project.hotel.input.Input;
import com.project.hotel.input.InputFactory;
import com.project.hotel.output.CliOutput;
import com.project.hotel.output.Output;
import com.project.hotel.output.ViewResolver;
import com.project.hotel.repository.*;
import com.project.hotel.service.Hotel;
import com.project.hotel.service.HotelService;

@SpringBootApplication
public class HotelApplication {

    private static HotelApplication hotelContext = new HotelApplication();
    private static String type;

    public static void main(String[] args) throws IOException {
        System.out.println("서비스 타입을 입력하세요.\n" +
                "1. cli 2. file"
        );
        type = InputFactory.cliInput().command();
        if (type.equals("web")) {
            SpringApplication.run(HotelApplication.class, args);
        } else {
            commandLineApp();
        }
    }

    public Input input() {
        try {
            if (type.equals("file")) {
                System.out.println("파일 경로를 입력하세요.");
                var path = InputFactory.cliInput().command();
                return InputFactory.fileInput(new File(path));
            }
            return InputFactory.cliInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ViewResolver viewResolver() {
        return new ViewResolver();
    }

    public final RoomRepository roomRepository = new RoomDb();

    public final AssetRepository assetRepository = new AssetDb();

    public final ReservationRepository reservationRepository = new ReservationRepositoryImpl();

    public HotelService hotelService() {
        return new Hotel(reservationRepository, roomRepository, assetRepository);
    }

    public Dispatcher dispatcher() {
        return new Dispatcher(controller());
    }

    public Controller controller() {
        return new Controller(hotelService(), sessionManager);
    }

    public final SessionManager sessionManager = new SessionManager();

    public Extractor extractor() {
        return new Extractor(input());
    }

    public static void commandLineApp() {
        Output output = CliOutput.create();

        var viewResolver = hotelContext.viewResolver();
        var extractor = hotelContext.extractor();
        var dispatcher = hotelContext.dispatcher();

        while (true) {
            try {
                // 1. 사용자로 부터 요청을 받는다.
                var request = extractor.getRequest();
                // 2. 정해진 요청을 통해서 컨트롤러를 사용한다.
                var response = dispatcher.handle(request);
                // 3. 전달받은 응답을 통해서 출력할 페이지를 선택한다.
                var page = viewResolver.getPage(response);
                // 4. 출력한다.
                output.println(page);
            } catch (Exception e){
                System.out.println("오류 발생 처음 부터 다시 시작합니다.");
            }
        }
    }
}
