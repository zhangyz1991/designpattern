package com.vick.designpattern.structure.proxy.staticproxy;

public class StaticProxyInvoker {
    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouse();
        BuyHouseProxy proxy = new BuyHouseProxy(buyHouse);
        proxy.buy();
    }
}
