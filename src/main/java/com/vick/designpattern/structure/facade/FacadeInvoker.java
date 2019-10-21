package com.vick.designpattern.structure.facade;

public class FacadeInvoker {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.run();
        computer.close();
    }
}
