package com.vick.designpattern.action.visitor;

public interface Visitor {
    void visit(Engineer staff);

    void visit(Manager staff);

    void visit(Contract staff);
}
