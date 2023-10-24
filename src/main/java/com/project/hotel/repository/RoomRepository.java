package com.project.hotel.repository;

import com.project.hotel.domain.Room;

import java.util.List;

public interface RoomRepository {

    List<Room> findRoomList();

    void reserve(Room room);



}
