package com.izavasconcelos.cloud.tema05.vehicles;

public class Bus implements Vehicle {

    private static final double PRICE = 1.59;

    @Override
    public double getTollPrice() {
        return PRICE;
    }
}
