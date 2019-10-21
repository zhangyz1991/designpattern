package com.vick.designpattern.structure.bridge;

public class Rectangle extends Shape {
    @Override
    public void draw() {
        color.bepaint("Rectangle");
    }
}
