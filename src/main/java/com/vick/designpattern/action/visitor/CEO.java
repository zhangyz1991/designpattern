package com.vick.designpattern.action.visitor;

public class CEO implements Visitor {

    @Override
    public void visit(Engineer engineer) {
        System.out.println("The engineer: " + engineer.getName() + " KPI:" + engineer.getKpi());
    }

    @Override
    public void visit(Manager manager) {
        System.out.println("The namager: " + manager.getName() + " KPI:" + manager.getKpi() + " products:" + manager.getProducts());
    }

    @Override
    public void visit(Contract contract) {
    }
}
