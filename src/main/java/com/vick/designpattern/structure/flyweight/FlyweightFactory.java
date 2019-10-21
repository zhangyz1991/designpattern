package com.vick.designpattern.structure.flyweight;

import java.util.HashMap;

public class FlyweightFactory {
    private static HashMap<String, Flyweight> pool = new HashMap<>();

    public static Flyweight getFlyweight(String extrinsic) {
        Flyweight flyweight = null;
        if (pool.containsKey(extrinsic)) {
            flyweight = pool.get(extrinsic);
            System.out.println("已有 " + extrinsic + " 直接从池中取--->");
        } else {
            flyweight = new ConcreteFlyWeight(extrinsic);
            pool.put(extrinsic, flyweight);
            System.out.println("创建 " + extrinsic + " 并从池中取出--->");
        }

        return flyweight;
    }
}
