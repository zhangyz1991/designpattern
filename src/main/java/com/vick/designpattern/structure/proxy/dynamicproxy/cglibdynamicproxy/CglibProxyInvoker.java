package com.vick.designpattern.structure.proxy.dynamicproxy.cglibdynamicproxy;

import com.vick.designpattern.structure.proxy.staticproxy.BuyHouse;
import com.vick.designpattern.structure.proxy.staticproxy.Shopping;

public class CglibProxyInvoker {
    public static void main(String[] args) {
        test1();
    }

    private static void test0() {
        Shopping shopping = new BuyHouse();
        CglibProxy cglibProxy = new CglibProxy();
        BuyHouse instance = (BuyHouse) cglibProxy.getInstance(shopping);
        instance.buy();
    }

    private static void test1() {
        CglibProxy cglibProxy = new CglibProxy();
        //Shopping instance1 = (Shopping) cglibProxy.getInstance1(BuyHouse.class);
        BuyHouse instance1 = (BuyHouse) cglibProxy.getInstance1(BuyHouse.class);
        instance1.buy();
    }
}
