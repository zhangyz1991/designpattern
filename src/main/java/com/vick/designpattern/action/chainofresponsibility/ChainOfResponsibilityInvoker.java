package com.vick.designpattern.action.chainofresponsibility;

public class ChainOfResponsibilityInvoker {

    public static void main(String[] args) {
        LeaderChain leaderChain;

        Document doc1 = new Document();
        doc1.setLevel(Document.DIVISIONMANAGER_LEVEL);
        doc1.setName("部门经理需要处理的档案");

        Document doc2 = new Document();
        doc2.setLevel(Document.DEPUTYMANAGER_LEVEL);
        doc2.setName("副总经理需要处理的档案");

        Document doc3 = new Document();
        doc3.setLevel(Document.PRESIDENT_LEVEL);
        doc3.setName("总经理需要处理的档案");

        leaderChain = new LeaderChain();
        leaderChain.process(doc1);
        leaderChain = new LeaderChain();
        leaderChain.process(doc2);
        leaderChain = new LeaderChain();
        leaderChain.process(doc3);

    }
}
