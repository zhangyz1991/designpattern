package com.vick.designpattern.action.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

public class LeaderChain {
    private List<Leader> leaders = new ArrayList<>();
    private int pos = 0;
    private int n = 0;

    {
        Leader divisionManager = new DivisionManager();
        Leader deputyManager = new DeputyManager();
        Leader president = new President();
        add(divisionManager);
        add(deputyManager);
        add(president);
    }

    public void add(Leader leader) {
        this.leaders.add(leader);
        n++;
    }

    public void process(Document doc) {
        internalProcess(doc);
    }

    private void internalProcess(Document doc) {
        if (pos < n) {
            Leader leader = leaders.get(pos++);
            leader.process(doc, this);
        }
    }

}
