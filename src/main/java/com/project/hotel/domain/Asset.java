package com.project.hotel.domain;

import com.project.hotel.repository.AssetRepository;

public class Asset{
    private long balance; //자산

    public long getBalance() {
        return balance;
    }

    public void displaybalance(){
        System.out.println("현재 자산은" + balance);
    }

    public void add(long amount){
        balance += amount;
    }

    public void minus(long amount){
        balance -= amount;
    }
}
