package com.vick.designpattern.action.visitor;

public class CTO implements Visitor {

    @Override
    public void visit(Engineer engineer) {
        System.out.println("The engineer: " + engineer.getName() + " codeLines:" + engineer.getCodeLines());
    }

    @Override
    public void visit(Manager manager) {
        System.out.println("The namager: " + manager.getName() + " products:" + manager.getProducts());
    }

    @Override
    public void visit(Contract contract) {
        System.out.println("The namager: " + contract.getName() + " kpi:" + contract.getKpi());
    }
}
