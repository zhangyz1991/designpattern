package com.vick.designpattern.action.mediator.disintermediation;

import java.util.ArrayList;
import java.util.List;

public class RedisDatabase extends AbstractDatabase {
    private List<String> dataSet = new ArrayList<>();

    @Override
    public void add(String data) {
        addData(data);
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
