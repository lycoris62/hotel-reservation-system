package com.project.hotel.controller;

public class Dispatcher {

    private Controller controller;

    public Dispatcher(Controller controller) {
        this.controller = controller;
    }

    public Object handle(Object request){
        return null;
    }
}
