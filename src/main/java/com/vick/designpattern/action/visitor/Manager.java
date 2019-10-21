package com.vick.designpattern.action.visitor;

public class Manager extends Staff {
    private int products = 0;

    {
        products = (int) Math.round(Math.random() * 10);
    }

    public Manager(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getProducts() {
        return products;
    }
}
