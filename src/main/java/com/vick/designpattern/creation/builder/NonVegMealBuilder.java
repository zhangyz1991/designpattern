package com.vick.designpattern.creation.builder;

public class NonVegMealBuilder extends MealBuilder {
    @Override
    public MealBuilder buildBurger() {
        this.meal.addItem(new ChickenBurger());
        return this;
    }

    @Override
    public MealBuilder buildColdDrink() {
        this.meal.addItem(new Pepsi());
        return this;
    }
}
