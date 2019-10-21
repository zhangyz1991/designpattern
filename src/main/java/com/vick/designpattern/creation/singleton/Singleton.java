package com.vick.designpattern.creation.singleton;

enum SingletonEnum {
    INSTANCE;

    public void whateverMethod() {

    }
}

public class Singleton {

}

class SingletonEager {
    private static SingletonEager instance = new SingletonEager();
    private boolean lazy = false;

    private SingletonEager() {
    }

    public static SingletonEager getInstance() {
        return instance;
    }
}

class SingletonLazyDCL {
    private volatile static SingletonLazyDCL instance;

    private SingletonLazyDCL() {
    }

    public static SingletonLazyDCL getInstance() {
        if (instance == null) {
            synchronized (SingletonLazyDCL.class) {
                if (instance == null) {
                    instance = new SingletonLazyDCL();
                }
            }
        }
        return instance;
    }
}

class SingletonLazyInnerClass {

    private SingletonLazyInnerClass() {
    }

    public static final SingletonLazyInnerClass getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final SingletonLazyInnerClass instance = new SingletonLazyInnerClass();
    }
}