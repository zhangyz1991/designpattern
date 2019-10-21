package com.vick.designpattern.structure.decorator;

public abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }
}
