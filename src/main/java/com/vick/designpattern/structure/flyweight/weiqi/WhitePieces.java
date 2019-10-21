package com.vick.designpattern.structure.flyweight.weiqi;

public class WhitePieces extends Pieces {

    public WhitePieces() {
        super("○");
        System.out.println("---White Pieces construct!");
    }

    @Override
    public void point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
