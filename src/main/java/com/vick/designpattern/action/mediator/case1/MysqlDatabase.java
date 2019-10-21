package com.vick.designpattern.action.mediator.case1;

import java.util.ArrayList;
import java.util.List;

public class MysqlDatabase extends AbstractDatabase {
    private List<String> dataSet = new ArrayList<>();

    public MysqlDatabase(AbstractMediator mediator) {
        super(mediator);
    }

    @Override
    public void add(String data) {
        addData(data);
        this.mediator.sync(AbstractDatabase.MYSQL, data);
    }

    @Override
    public void addData(String data) {
        System.out.println("Mysql add data:" + data);
        this.dataSet.add(data);
    }

    public void select() {
        System.out.println("--Mysql search ,Data:" + this.dataSet.toString());
    }
}
