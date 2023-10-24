package com.project.hotel.domain;

import java.time.LocalDate;

public class Room {
    private String id;
    private LocalDate date;

    private String name;
    private double area;

    private int fee;

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
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