package com.vick.designpattern.action.mediator.disintermediation;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class MysqlDatabase extends AbstractDatabase {
    private List<String> dataSet = new ArrayList<>();
    @Setter
    private RedisDatabase redisDatabase;
    @Setter
    private ElasticDatabase elasticDatabase;

    @Override
    public void add(String data) {
        addData(data);
        this.redisDatabase.addData(data);//data synchronization to redis
        this.elasticDatabase.addData(data);//data synchronization to elasticDatabase
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
