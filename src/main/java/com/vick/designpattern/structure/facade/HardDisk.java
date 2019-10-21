package com.vick.designpattern.structure.facade;

public class HardDisk implements ElectronicComponent {
    @Override
    public void run() {
        System.out.println("HardDisk start run");
    }

    @Override
    public void close() {
        System.out.println("HardDisk close");
    }
}
