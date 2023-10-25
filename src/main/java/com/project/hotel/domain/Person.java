package com.project.hotel.domain;

public class Person {

    private String id;
    private String password;
    private String number;
    private String name;
    private Role role;
    private int money;

    public Person(String id, String password, String number, String name, Role role, int money) {
        this.id = id;
        this.password = password;
        this.number = number;
        this.name = name;
        this.role = role;
        this.money = money;
    }

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

    public void plusMoney(int money) {
        this.money += money;
    }

    public void minusMoney(int money) {
        this.money -= money;
    }

    public int getMoney() {
        return money;
    }
}