package com.project.hotel.domain;

import com.project.hotel.repository.AssetRepository;

public class Asset implements AssetRepository {

    private long balance; //자산

    public long getBalance() {
        return balance;
    }

    public void add(long amount){
        balance += amount;
    }

    public void minus(long amount){
        balance -= amount;
    }

    @Override
    public void plus(int price) {//예약 가격 예약 취소

    }

    @Override
    public void minus(int price) {

    }
}
