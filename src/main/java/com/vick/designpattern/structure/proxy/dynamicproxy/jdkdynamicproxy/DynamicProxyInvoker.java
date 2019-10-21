package com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy;

import com.vick.designpattern.structure.proxy.staticproxy.BuyHouse;
import com.vick.designpattern.structure.proxy.staticproxy.Shopping;

import java.lang.reflect.Proxy;

public class DynamicProxyInvoker {
    public static void main(String[] args) {
        Shopping buyHouse = new BuyHouse();
        Shopping buyHouseProxy = (Shopping) Proxy.newProxyInstance(Shopping.class.getClassLoader(), new Class[]{Shopping.class},
                new DynamicProxyHandler(buyHouse));
        buyHouseProxy.buy();
    }
}
