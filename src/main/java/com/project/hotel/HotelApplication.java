package com.project.hotel;

import com.project.hotel.common.SessionManager;
import com.project.hotel.controller.Controller;
import com.project.hotel.controller.Dispatcher;
import com.project.hotel.input.CliInput;
import com.project.hotel.input.Extractor;
import com.project.hotel.output.CliOutput;
import com.project.hotel.output.Output;
import com.project.hotel.output.ViewResolver;
import com.project.hotel.service.Hotel;
import com.project.hotel.service.HotelService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class HotelApplication {

    private static HotelApplication hotelContext = new HotelApplication();

    public static void main(String[] args) throws IOException {
        var input = new BufferedReader(new InputStreamReader(System.in));
        if (input.readLine().equals("web")) {
            SpringApplication.run(HotelApplication.class, args);
        } else {
            input.close();
            commandLineApp();
        }
    }

    public ViewResolver viewResolver() {
        return new ViewResolver();
    }

    public HotelService hotelService(){
        return new Hotel();
    }

    public Dispatcher dispatcher(){
        return new Dispatcher(controller());
    }
    public Controller controller(){
        return new Controller(hotelService(), sessionManager());
    }

    public SessionManager sessionManager(){
        return new SessionManager();
    }

    public Extractor extractor(){
        return new Extractor(CliInput.create());
    }

    public static void commandLineApp() {
        Output output = CliOutput.create();

        var viewResolver = hotelContext.viewResolver();
        var extractor = hotelContext.extractor();
        var dispatcher = hotelContext.dispatcher();

        while (true) {
            // 1. 사용자로 부터 요청을 받는다.
            var request = extractor.getRequest();
            // 2. 정해진 요청을 통해서 컨트롤러를 사용한다.
            var response = dispatcher.handle(request);
            // 3. 전달받은 응답을 통해서 출력할 페이지를 선택한다.
            var page = viewResolver.getPage(response);
            // 4. 출력한다.
            output.println(page);
        }
    }
}
