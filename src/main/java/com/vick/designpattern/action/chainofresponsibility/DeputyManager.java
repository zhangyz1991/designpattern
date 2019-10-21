package com.vick.designpattern.action.chainofresponsibility;

public class DeputyManager extends Leader {
    @Override
    public void process(Document doc, LeaderChain leaderChain) {
        if (doc == null)
            return;
        if (doc.getLevel() == Document.DEPUTYMANAGER_LEVEL) {
            System.out.println("副总经理处理文件: " + doc.getName());
        }

        leaderChain.process(doc);
    }
}
