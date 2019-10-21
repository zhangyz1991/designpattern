package com.vick.designpattern.action.mediator.disintermediation;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ElasticDatabase extends AbstractDatabase {
    private List<String> dataSet = new ArrayList();
    @Setter
    private MysqlDatabase mysqlDatabase;

    @Override

    public void add(String data) {
        addData(data);
        this.mysqlDatabase.addData(data);//data synchronization to mysqlDatabase
    }

    @Override
    public void addData(String data) {
        System.out.println("Elastic add data:" + data);
        this.dataSet.add(data);
    }

    public void count() {
        int count = this.dataSet.size();
        System.out.println("--Elasticsearch statistics,there are currently " + count + " pieces of data,data:" + this.dataSet.toString());
    }
}
