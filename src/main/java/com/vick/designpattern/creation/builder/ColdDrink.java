package com.vick.designpattern.creation.builder;

public abstract class ColdDrink extends Item {
    {
        super.packing = new Bottle();
    }

}
