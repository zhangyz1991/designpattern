package com.vick.designpattern.action.visitor;

public class Engineer extends Staff {

    private long codeLines = 0;

    {
        codeLines = Math.round(Math.random() * 100000);
    }

    public Engineer(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public long getCodeLines() {
        return codeLines;
    }
}
