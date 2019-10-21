package com.vick.designpattern.structure.proxy.staticproxy;

public class BuyHouse implements Shopping {
    @Override
    public void buy() {
        System.out.println("buy a house!");
    }
}
