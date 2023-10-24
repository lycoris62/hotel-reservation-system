package com.project.hotel.domain;

public class Asset {

    private long balance;

    public long getBalance() {
        return balance;
    }

    public void add(long amount){
        balance += amount;
    }

    public void minus(long amount){
        balance -= amount;
    }
}
