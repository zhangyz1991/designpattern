package com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy.sample2;

import com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy.sample2.realsubject.RealSubjectA;
import com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy.sample2.realsubject.RealSubjectB;
import com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy.sample2.subject.SubjectA;
import com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy.sample2.subject.SubjectB;

import java.lang.reflect.Proxy;

/**
 * @author Vick
 * @date 2020/9/23
 */
public class TestDynamicProxy {
    public static void main(String[] args) {
        RealSubjectA realA = new RealSubjectA();
        //生成一个业务A的动态代理对象
        SubjectA proxySubjectA = (SubjectA) Proxy.newProxyInstance(SubjectA.class.getClassLoader(),
                new Class[]{SubjectA.class},
                new LogHandler(realA));
        RealSubjectB realB = new RealSubjectB();
        //生成一个业务B的动态代理对象
        SubjectB proxySubjectB = (SubjectB) Proxy.newProxyInstance(SubjectB.class.getClassLoader(),
                new Class[]{SubjectB.class},
                new LogHandler(realB));
        proxySubjectA.setUser("heaven", "123456");
        proxySubjectB.sayHello("heaven");
    }
}
