package com.vick.designpattern.structure.flyweight.weiqi;

public abstract class Pieces {
    protected int x;
    protected int y;
    protected String power;

    public Pieces(String power) {
        this.power = power;
    }

    public abstract void point(int x, int y);

    public void show() {
        System.out.println(this.power + "(" + this.x + "," + this.y + ")");
    }
}
