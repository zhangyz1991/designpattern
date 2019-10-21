package com.vick.designpattern.creation.builder;

public abstract class MealBuilder {

    protected Meal meal = new Meal();

    public abstract MealBuilder buildBurger();

    public abstract MealBuilder buildColdDrink();

    public Meal getMeal() {
        return this.meal;
    }

    @Deprecated
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    @Deprecated
    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
