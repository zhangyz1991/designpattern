package com.vick.designpattern.structure.adapter.classadapter;

/**
 * 客户端知道的某功能的实现
 *
 * @author Vick
 * @date 2020/9/24
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("ConcreteTarget.request()");
    }
}
