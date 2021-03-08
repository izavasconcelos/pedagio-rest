package com.izavasconcelos.cloud.tema05.vehicles;

public class Motorcycle implements Vehicle {

    private static final double PRICE = 1.0;

    @Override
    public double getTollPrice() {
        return PRICE;
    }
}
