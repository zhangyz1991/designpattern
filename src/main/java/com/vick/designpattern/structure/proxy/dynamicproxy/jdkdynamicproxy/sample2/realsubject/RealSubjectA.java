package com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy.sample2.realsubject;

import com.vick.designpattern.structure.proxy.dynamicproxy.jdkdynamicproxy.sample2.subject.SubjectA;

/**
 * @author Vick
 * @date 2020/9/23
 */
public class RealSubjectA implements SubjectA {

    @Override
    public void setUser(String name, String password) {
        System.out.println("-------------set user,name:" + name + " password:" + password + "-------------");
    }
}
