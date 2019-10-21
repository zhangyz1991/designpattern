package com.vick.designpattern.action.mediator.case1;

import java.util.ArrayList;
import java.util.List;

public class RedisDatabase extends AbstractDatabase {
    private List<String> dataSet = new ArrayList<>();

    public RedisDatabase(AbstractMediator mediator) {
        super(mediator);
    }

    @Override
    public void add(String data) {
        addData(data);
        this.mediator.sync(AbstractDatabase.REDIS, data);
    }

    @Override
    public void addData(String data) {
        System.out.println("Redis add daa:" + data);
        this.dataSet.add(data);
    }

    public void cache() {
        System.out.println("--Redis cache data:" + this.dataSet.toString());
    }
}
