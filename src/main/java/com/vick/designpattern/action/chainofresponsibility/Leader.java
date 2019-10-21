package com.vick.designpattern.action.chainofresponsibility;

public abstract class Leader {

    public abstract void process(Document doc, LeaderChain leaderChain);
}
