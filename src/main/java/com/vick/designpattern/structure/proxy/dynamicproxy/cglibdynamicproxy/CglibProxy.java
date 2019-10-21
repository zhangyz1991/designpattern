package com.vick.designpattern.structure.proxy.dynamicproxy.cglibdynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Object target;

    public Object getInstance(final Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    public Object getInstance1(Class clazz) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("确认买主信息!");
        System.out.println("过户");
        //method 1:
        Object result = methodProxy.invokeSuper(object, args);
        //method 2:
        //Object result = method.invoke(target, args);
        System.out.println("装修");
        return result;


        //1、正确：一般通过代理实例上反射调用代理方法来调用到目标方法。
        //Object returnVal = proxyMethod.invokeSuper(proxyObj,args);
        //2、错误：在代理实例上反射调用目标方法，会递归调用到代理方法，陷入死循环，最终报错
        //*** java.lang.instrument ASSERTION FAILED ***: "!errorOutstanding" with message transform method call failed at JPLISAgent.c line: 844
        //Object returnVal = targetMethod.invoke(proxyObj,args);
        //3、错误：在代理实例上反射调用目标方法。最终报错，结果同2
        //Object returnVal = proxyMethod.invoke(proxyObj,args);
        //4、正确：构造增强器/拦截器实例时，传入目标类实例targetObj，则可以在此通过目标类实例反射调用目标方法。
        //Object returnVal = targetMethod.invoke(targetObj,args);
    }
}
