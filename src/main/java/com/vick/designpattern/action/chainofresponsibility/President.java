package com.vick.designpattern.action.chainofresponsibility;

public class President extends Leader {
    @Override
    public void process(Document doc, LeaderChain leaderChain) {
        if (doc == null)
            return;
        if (doc.getLevel() == Document.PRESIDENT_LEVEL) {
            System.out.println("总经理处理文件: " + doc.getName());
        }

        leaderChain.process(doc);
    }
}
