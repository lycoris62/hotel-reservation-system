package com.project.hotel.domain;

public class Person {

    private String id;

    private String password;
    private String number;
    private String name;
    private Role role;

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}