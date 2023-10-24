package com.project.hotel.controller;

import com.project.hotel.common.SessionManager;
import com.project.hotel.domain.Person;
import com.project.hotel.input.Input;
import com.project.hotel.output.Output;
import com.project.hotel.output.response.ErrorResponse;
import com.project.hotel.service.HotelService;

public class Controller {

    private final Input input;
    private final Output output;
    private final HotelService hotel;
    private final SessionManager sessionManager;

    public Controller(Input input, Output output, HotelService hotel, SessionManager sessionManager) {
        this.input = input;
        this.output = output;
        this.hotel = hotel;
        this.sessionManager = sessionManager;
    }

    public Object reserve(String roomId, String userId, String password){
        try {
            Person user = sessionManager.getUser(userId, password);
            var data = hotel.reserve(user);
            return null;
        } catch (Exception e){
            return new ErrorResponse(e.getMessage());
        }
    }
}
