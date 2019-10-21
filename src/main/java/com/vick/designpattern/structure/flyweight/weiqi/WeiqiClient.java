package com.vick.designpattern.structure.flyweight.weiqi;

import java.util.Random;

public class WeiqiClient {

    public static void main(String[] args) {
        WeiqiPiecesFactory factory = WeiqiPiecesFactory.getInstance();
        Random random = new Random();
        int radom = 0;
        Pieces pieces = null;

        for (int i = 0; i < 10; i++) {
            radom = random.nextInt(2);
            switch (radom) {
                case 0:
                    pieces = factory.getPiecesObject("White");
                    break;
                case 1:
                    pieces = factory.getPiecesObject("Black");
                    break;
            }

            if (pieces != null) {
                pieces.point(i, random.nextInt(15));
                pieces.show();
            }
        }
    }
}
