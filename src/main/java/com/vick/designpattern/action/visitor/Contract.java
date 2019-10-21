package com.vick.designpattern.action.visitor;

public class Contract extends Staff {

    public Contract(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
