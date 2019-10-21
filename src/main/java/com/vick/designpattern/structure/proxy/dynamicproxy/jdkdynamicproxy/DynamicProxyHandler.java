package com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
    private Object object;

    public DynamicProxyHandler(final Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("确认买主信息");
        System.out.println("过户");
        Object result = method.invoke(object, args);
        System.out.println("装修");
        return result;
    }
}
