package com.vick.designpattern.action.chainofresponsibility;

public class DivisionManager extends Leader {
    @Override
    public void process(Document doc, LeaderChain leaderChain) {
        if (doc == null)
            return;
        if (doc.getLevel() == Document.DIVISIONMANAGER_LEVEL) {
            System.out.println("部门经理处理文件: " + doc.getName());
        }

        leaderChain.process(doc);
    }
}
