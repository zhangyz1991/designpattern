package com.vick.designpattern.creation.builder;

public abstract class Burger extends Item {
    {
        super.packing = new Wrapper();
    }

}
