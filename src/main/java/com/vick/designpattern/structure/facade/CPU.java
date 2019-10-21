package com.vick.designpattern.structure.facade;

public class CPU implements ElectronicComponent {
    @Override
    public void run() {
        System.out.println("CPU start run");
    }

    @Override
    public void close() {
        System.out.println("CPU close");
    }
}
