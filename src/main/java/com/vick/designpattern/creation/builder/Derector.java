package com.vick.designpattern.creation.builder;

public class Derector {
    public MealBuilder builder;

    public Derector(MealBuilder builder) {
        this.builder = builder;
    }

    public Meal construct() {
        builder.buildBurger();
        builder.buildColdDrink();
        return builder.getMeal();
    }
}
