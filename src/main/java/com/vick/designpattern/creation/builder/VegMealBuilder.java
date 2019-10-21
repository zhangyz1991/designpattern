package com.vick.designpattern.creation.builder;

public class VegMealBuilder extends MealBuilder {
    @Override
    public MealBuilder buildBurger() {
        this.meal.addItem(new VegBurger());
        return this;
    }

    @Override
    public MealBuilder buildColdDrink() {
        this.meal.addItem(new Coke());
        return this;
    }
}
