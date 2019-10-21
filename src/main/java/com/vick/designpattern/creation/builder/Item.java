package com.vick.designpattern.creation.builder;

public abstract class Item {
    protected String name;
    protected double price;
    protected Packing packing;

    public String getName() {
        return this.name;
    }

    public Packing packing() {
        return this.packing;
    }

    public double price() {
        return this.price;
    }
}
