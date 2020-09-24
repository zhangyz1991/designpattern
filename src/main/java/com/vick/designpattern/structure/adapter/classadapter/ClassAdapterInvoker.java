package com.vick.designpattern.structure.adapter.classadapter;

/**
 * 客户端
 *
 * @author Vick
 * @date 2020/9/24
 */
public class ClassAdapterInvoker {

    public static void main(String[] args) {
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        Target adapterTarget = new Adapter();
        adapterTarget.request();
    }

}
