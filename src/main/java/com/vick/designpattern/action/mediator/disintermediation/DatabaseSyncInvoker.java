package com.vick.designpattern.action.mediator.disintermediation;

public class DatabaseSyncInvoker {

    public static void main(String[] args) {
        MysqlDatabase mysqlDatabase = new MysqlDatabase();
        RedisDatabase redisDatabase = new RedisDatabase();
        ElasticDatabase elasticDatabase = new ElasticDatabase();

        mysqlDatabase.setRedisDatabase(redisDatabase);
        mysqlDatabase.setElasticDatabase(elasticDatabase);
        elasticDatabase.setMysqlDatabase(mysqlDatabase);

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
