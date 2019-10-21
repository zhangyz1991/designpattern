package com.vick.designpattern.structure.proxy.staticproxy;

public class BuyHouseProxy implements Shopping {
    private Shopping shopping;

    public BuyHouseProxy(Shopping shopping) {
        this.shopping = shopping;
    }

    @Override
    public void buy() {
        System.out.println("确认买主信息");
        System.out.println("过户");
        shopping.buy();
        System.out.println("装修");
    }
}
