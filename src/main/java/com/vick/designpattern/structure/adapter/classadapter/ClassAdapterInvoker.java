package com.vick.designpattern.structure.adapter.classadapter;

public class ClassAdapterInvoker {
    public static void main(String[] args) {
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        Target adapterTarget = new Adapter();
        adapterTarget.request();
    }
}
