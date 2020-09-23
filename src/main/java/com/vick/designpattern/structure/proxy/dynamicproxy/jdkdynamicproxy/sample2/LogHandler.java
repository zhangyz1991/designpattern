package com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy.sample2;

/**
 * @author Vick
 * @date 2020/9/23
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志打印handler，打印调用代理对象的方法及其参数值
 **/
public class LogHandler implements InvocationHandler {
    private Object proxied;

    LogHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin to invoke method:" + method.getName() + " params:" + Arrays.toString(args));
        Object result = method.invoke(proxied, args);
        System.out.println("invoke " + method.getName() + " end");
        return result;
    }
}

