package com.vick.designpattern.structure.facade;

public class Memory implements ElectronicComponent {
    @Override
    public void run() {
        System.out.println("Memory start run");
    }

    @Override
    public void close() {
        System.out.println("Memory close");
    }
}
