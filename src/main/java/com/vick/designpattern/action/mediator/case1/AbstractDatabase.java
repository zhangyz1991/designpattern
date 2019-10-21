package com.vick.designpattern.action.mediator.case1;

public abstract class AbstractDatabase {
    public static final String REDIS = "reids";
    public static final String MYSQL = "mysql";
    public static final String ELASTICSEARCH = "elasticsearch";

    protected AbstractMediator mediator;

    public AbstractDatabase(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void add(String data);

    public abstract void addData(String data);
}
