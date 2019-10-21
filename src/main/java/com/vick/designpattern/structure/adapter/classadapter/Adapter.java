package com.vick.designpattern.structure.adapter.classadapter;

public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.innateRequest();
    }
}
