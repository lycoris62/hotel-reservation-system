package com.project.hotel.repository;

import com.project.hotel.domain.Asset;

public class AssetDb implements AssetRepository{

    private final Asset asset = new Asset();
    @Override
    public void plus(long price) {
        asset.add(price);
    }
    @Override
    public void minus(long price) {
        asset.minus(price);
    }

}
