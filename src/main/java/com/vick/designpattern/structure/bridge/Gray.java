package com.vick.designpattern.structure.bridge;

public class Gray implements Color {
    @Override
    public void bepaint(String shape) {
        System.out.println("灰色的" + shape);
    }
}
