package com.project.hotel;

import com.project.hotel.input.CliInput;
import com.project.hotel.input.Input;
import com.project.hotel.output.CliOutput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class HotelApplication {

    public static void main(String[] args) throws IOException {
        var input = new BufferedReader(new InputStreamReader(System.in));
        if (input.readLine().equals("web")) {
            SpringApplication.run(HotelApplication.class, args);
        } else {
            commandLineApp();
        }
    }

    public static void commandLineApp() {
        var input = CliInput.create();
        var output = CliOutput.create();

        while(true){
            // 구현하는 곳
        }
    }
}
