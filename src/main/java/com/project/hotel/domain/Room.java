package com.project.hotel.domain;

import java.time.LocalDate;

public class Room {
    private LocalDate date;
    private Person person;
    private double area;

    private int fee;

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