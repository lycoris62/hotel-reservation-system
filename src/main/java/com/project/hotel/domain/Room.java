package com.project.hotel.domain;

import java.time.LocalDate;

public class Room {
    private String id;
    private String name;
    private LocalDate date;
    private Person person;
    private double area;
    private int fee;
    public Room(String id) {
        this.id = id;
    }

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
}