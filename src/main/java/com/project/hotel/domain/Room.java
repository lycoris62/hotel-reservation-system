package com.project.hotel.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//이름, 크기, 숙박가격, 예약가능 유무
public class Room {
    private int id;
    private final Set<LocalDate> reservedDates;
    private String name;
    private double area;
    private int fee;

    public Room(int id,Set<LocalDate> reservedDates,String name, double area, int fee){
        this.id = id;
        this.reservedDates = reservedDates;
        this.name = name;
        this.area = area;
        this.fee = fee;

    }

    public int getId() {
        return id;
    }

    public boolean isReserved(LocalDate date) {
        return reservedDates.contains(date);
    }

    public void reserve(LocalDate date) {
        reservedDates.add(date);
    }

    public void cancelReserve(LocalDate date) {
        reservedDates.remove(date);
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public int getFee() {
        return fee;
    }
}