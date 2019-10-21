package com.vick.designpattern.action.mediator.case1;

public class DatabaseSyncInvoker {

    public static void main(String[] args) {
        AbstractMediator mediator = new SyncMediator();
        MysqlDatabase mysqlDatabase = new MysqlDatabase(mediator);
        RedisDatabase redisDatabase = new RedisDatabase(mediator);
        ElasticDatabase elasticDatabase = new ElasticDatabase(mediator);

        mediator.setElasticDatabase(elasticDatabase);
        mediator.setMysqlDatabase(mysqlDatabase);
        mediator.setRedisDatabase(redisDatabase);

        System.out.println("---Mysql add data 1,and will synchronization to Elastic & Redis---");
        mysqlDatabase.add("1");

        mysqlDatabase.select();
        redisDatabase.cache();
        elasticDatabase.count();

        System.out.println("\n---Redis add data 2,and won't synchronization to other databases---");
        redisDatabase.add("2");

        mysqlDatabase.select();
        redisDatabase.cache();
        elasticDatabase.count();

        System.out.println("\n---Elastic add data 3,and will only synchronization to Mysql---");
        elasticDatabase.add("3");

        mysqlDatabase.select();
        redisDatabase.cache();
        elasticDatabase.count();
    }
}
