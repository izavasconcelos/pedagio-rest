package com.izavasconcelos.cloud.tema05.vehicles;

public class Truck implements Vehicle {

    private static final double PRICE = 3.95;

    @Override
    public double getTollPrice() {
        return PRICE;
    }
}
