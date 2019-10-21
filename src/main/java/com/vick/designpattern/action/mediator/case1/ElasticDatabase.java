package com.vick.designpattern.action.mediator.case1;

import java.util.ArrayList;
import java.util.List;

public class ElasticDatabase extends AbstractDatabase {
    private List<String> dataSet = new ArrayList<>();

    public ElasticDatabase(AbstractMediator mediator) {
        super(mediator);
    }

    @Override
    public void add(String data) {
        addData(data);
        this.mediator.sync(AbstractDatabase.ELASTICSEARCH, data);
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
