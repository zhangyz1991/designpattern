package com.vick.designpattern.structure.flyweight;

public class ConcreteFlyWeight extends Flyweight {

    public ConcreteFlyWeight(String extrinsic) {
        super(extrinsic);
    }

    @Override
    public void operate(int extrinsic) {
        System.out.println("具体Flyweight:" + extrinsic);
    }
}
