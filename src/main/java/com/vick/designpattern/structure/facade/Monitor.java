package com.vick.designpattern.structure.facade;

public class Monitor implements ElectronicComponent {
    @Override
    public void run() {
        System.out.println("Monitor start run");
    }

    @Override
    public void close() {
        System.out.println("Monitor close");
    }
}
