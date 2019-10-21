package com.vick.designpattern.action.mediator.case1;

public class SyncMediator extends AbstractMediator {
    @Override
    public void sync(String databaseName, String data) {
        switch (databaseName) {
            case AbstractDatabase.MYSQL:
                this.redisDatabase.addData(data);
                this.elasticDatabase.addData(data);
                break;
            case AbstractDatabase.ELASTICSEARCH:
                this.mysqlDatabase.addData(data);
                break;
            case AbstractDatabase.REDIS:
                break;
            default:
                break;
        }
    }
}
