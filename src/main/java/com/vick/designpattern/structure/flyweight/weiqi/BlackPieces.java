package com.vick.designpattern.structure.flyweight.weiqi;

public class BlackPieces extends Pieces {

    public BlackPieces() {
        super("●");
        System.out.println("---Black Pieces construct!");
    }

    @Override
    public void point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
