package com.vick.designpattern.structure.composite;

public abstract class Entry {

    public abstract String getName();

    public abstract int getSize();

    public abstract void pringlList(String prefix);

    @Override
    public String toString() {
        return getName();
    }
}


