package com.vick.designpattern.creation.builder;

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
