package com.vick.designpattern.structure.bridge;

public class Circle extends Shape {
    @Override
    public void draw() {
        color.bepaint("Circle");
    }
}
