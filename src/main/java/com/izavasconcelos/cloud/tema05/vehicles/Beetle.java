package com.izavasconcelos.cloud.tema05.vehicles;

public class Beetle implements Vehicle {

    private static final double PRICE = 2.11;

    @Override
    public double getTollPrice() {
        return PRICE;
    }
}
