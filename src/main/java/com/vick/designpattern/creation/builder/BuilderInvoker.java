package com.vick.designpattern.creation.builder;

public class BuilderInvoker {

    public static void main(String[] args) {
        MealBuilder mealBuilder = new NonVegMealBuilder();
        Meal nonVegMeal = new Derector(mealBuilder).construct();
        System.out.println("Non-Veg meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost : " + nonVegMeal.getCost());

        mealBuilder = new VegMealBuilder();
        Meal vegMeal = new Derector(mealBuilder).construct();
        System.out.println("\n\nVeg meal");
        vegMeal.showItems();
        System.out.println("Total Cost : " + vegMeal.getCost());
    }
}
