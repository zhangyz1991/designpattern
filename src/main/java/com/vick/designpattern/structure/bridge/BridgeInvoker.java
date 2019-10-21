package com.vick.designpattern.structure.bridge;

public class BridgeInvoker {
    public static void main(String[] args) {
        Color white = new White();
        Shape square = new Square();
        square.setColor(white);
        square.draw();
    }
}
