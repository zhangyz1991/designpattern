package com.vick.designpattern.action.visitor;

public class VisitorInvoker {

    public static void main(String[] args) {
        BusinessReport report = new BusinessReport();
        System.out.println("---CEO check report");
        report.show(new CEO());
        System.out.println("---CTO check report");
        report.show(new CTO());
    }
}
