package com.vick.designpattern.structure.facade;

public class Computer {
    private CPU cpu;
    private Memory memory;
    private HardDisk hardDisk;
    private Monitor monitor;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
        hardDisk = new HardDisk();
        monitor = new Monitor();
    }

    public void run() {
        this.cpu.run();
        this.memory.run();
        this.hardDisk.run();
        this.monitor.run();
    }

    public void close() {
        this.monitor.close();
        this.hardDisk.close();
        this.memory.close();
        this.cpu.close();
    }
}
