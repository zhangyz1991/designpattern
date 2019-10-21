package com.vick.designpattern.structure.decorator;

public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void biuBefore() {
        System.out.println("ready ? go");
    }

    public void biuAfter() {
        System.out.println("over ");
    }

    @Override
    public void biu() {
        biuBefore();
        super.component.biu();
        biuAfter();
    }
}
