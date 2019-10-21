package com.vick.designpattern.action.visitor;

import java.util.Random;

public abstract class Staff {
    private String name;
    private int kpi;

    public Staff(String name) {
        this.name = name;
        kpi = new Random().nextInt(10);
    }

    public String getName() {
        return name;
    }

    public int getKpi() {
        return kpi;
    }

    public abstract void accept(Visitor visitor);
}
