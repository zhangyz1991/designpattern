package com.vick.designpattern.structure.composite;

public class File extends Entry {
    private String name;
    private int size;

    public File(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void pringlList(String prefix) {
        System.out.println(prefix + "/" + this);
    }
}
