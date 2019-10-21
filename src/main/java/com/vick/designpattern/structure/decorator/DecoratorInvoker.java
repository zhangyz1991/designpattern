package com.vick.designpattern.structure.decorator;

public class DecoratorInvoker {
    public static void main(String[] args) {
        ConcreteDecorator concreteDecorator = new ConcreteDecorator(new ConcreteComponent());
        concreteDecorator.biu();
    }
}
