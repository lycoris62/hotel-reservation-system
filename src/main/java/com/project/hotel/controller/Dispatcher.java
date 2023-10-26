package com.project.hotel.controller;

import com.project.hotel.input.request.DeleteReserveRequest;
import com.project.hotel.input.request.GetReserveListRequest;
import com.project.hotel.input.request.ReserveRequest;

public class Dispatcher {

    private Controller controller;

    public Dispatcher(Controller controller) {
        this.controller = controller;
    }

    public Object handle(Object request) {
        if (request instanceof ReserveRequest reserveRequest) {
            var user = reserveRequest.person();
            var id = user.getId();
            var password = user.getPassword();
            var roomName = reserveRequest.roomName();
            var time = reserveRequest.dateTime();
            return controller.reserve(roomName, id, password, time);
        }

        if (request instanceof GetReserveListRequest getReserveListRequest) {
            var user = getReserveListRequest.person();
            var id = user.getId();
            var password = user.getPassword();
            return controller.getReservationList(id, password);
        }

        if (request instanceof DeleteReserveRequest deleteReserveRequest) {
            var user = deleteReserveRequest.person();
            var id = user.getId();
            var password = user.getPassword();
            var reservation = deleteReserveRequest.reservationId();
            return controller.deleteReservation(id, password, reservation);
        }


        throw new RuntimeException("값 오류");
    }
}
