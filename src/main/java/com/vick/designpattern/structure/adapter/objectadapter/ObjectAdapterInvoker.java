package com.vick.designpattern.structure.adapter.objectadapter;

public class ObjectAdapterInvoker {
    public static void main(String[] args) {
        Target objectAdapter = new Adapter();
        objectAdapter.request();
    }
}
