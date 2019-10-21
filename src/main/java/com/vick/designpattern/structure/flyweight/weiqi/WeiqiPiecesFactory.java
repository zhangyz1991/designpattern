package com.vick.designpattern.structure.flyweight.weiqi;

import java.util.Hashtable;

public class WeiqiPiecesFactory {

    private Hashtable<String, Pieces> cache = new Hashtable<>();

    private WeiqiPiecesFactory() {
    }

    public static WeiqiPiecesFactory getInstance() {
        return FactoryHolder.instance;
    }

    public Pieces getPiecesObject(String power) {
        Pieces pieces = this.cache.get(power);
        if (pieces == null) {
            switch (power) {
                case "White":
                    pieces = new WhitePieces();
                    break;
                case "Black":
                    pieces = new BlackPieces();
                    break;
                default:
                    break;
            }
        }

        if (pieces != null) {
            this.cache.put(power, pieces);
        }

        return pieces;
    }

    private static class FactoryHolder {
        private static final WeiqiPiecesFactory instance = new WeiqiPiecesFactory();
    }

}