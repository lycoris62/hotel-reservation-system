package com.project.hotel.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Room {
    private String id;
    private final Set<LocalDate> reservedDates = new HashSet<>();
    private String name;
    private double area;

    private int fee;

    public String getId() {
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