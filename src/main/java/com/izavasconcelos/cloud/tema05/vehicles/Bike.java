package com.izavasconcelos.cloud.tema05.vehicles;

public class Bike implements Vehicle {

    private static final double PRICE = 0.49;

    @Override
    public double getTollPrice() {
        return PRICE;
    }
}
