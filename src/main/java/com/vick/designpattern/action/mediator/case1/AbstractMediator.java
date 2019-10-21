package com.vick.designpattern.action.mediator.case1;

import lombok.Setter;

public abstract class AbstractMediator {
    @Setter
    protected MysqlDatabase mysqlDatabase;
    @Setter
    protected RedisDatabase redisDatabase;
    @Setter
    protected ElasticDatabase elasticDatabase;

    public abstract void sync(String databaseName, String data);
}
