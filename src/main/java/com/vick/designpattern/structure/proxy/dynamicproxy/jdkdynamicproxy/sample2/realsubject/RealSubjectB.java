package com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy.sample2.realsubject;

import com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy.sample2.subject.SubjectB;

/**
 * @author Vick
 * @date 2020/9/23
 */
public class RealSubjectB implements SubjectB {

    @Override
    public void sayHello(String name) {
        System.out.println("--------------say hello:" + name + "-------------");
    }
}