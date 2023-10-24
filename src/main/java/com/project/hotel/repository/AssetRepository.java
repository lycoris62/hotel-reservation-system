package com.project.hotel.repository;

import org.springframework.util.Assert;

public interface AssetRepository{

    void plus(int price);

    void minus(int price);

}
