package com.project.hotel.domain;

import com.project.hotel.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

public class Room implements RoomRepository {
    private LocalDate date; //날짜
    private Person person; // 고객
    private double area; // 크기
    private int fee; // 숙박비

    public LocalDate getDate() {
        return date;
    }

    public Person getPerson() {
        return person;
    }

    public double getArea() {
        return area;
    }

    public int getFee() {
        return fee;
    }

    @Override
    public List<Room> findRoomList() {
        return null;
    }

    @Override
    public void reserve(Room room) {

    }
}