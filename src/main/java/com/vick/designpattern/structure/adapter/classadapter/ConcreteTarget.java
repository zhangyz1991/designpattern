package com.vick.designpattern.structure.adapter.classadapter;

public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("ConcreteTarget.request()");
    }
}
