package com.vick.designpattern.creation.factory;

interface Factory {
    Product createProduct();
}

public class FactoryMethodInvoker {
    public static void main(String[] args) {
        Factory factoryA = new PlaneFactory();
        Product productA = factoryA.createProduct();
        System.out.println(productA.getProductName());

        Factory factoryB = new BoatFactory();
        Product productB = factoryB.createProduct();
        System.out.println(productB.getProductName());
    }
}

class PlaneFactory implements Factory {

    @Override
    public Product createProduct() {
        return new Plane();
    }
}

class BoatFactory implements Factory {

    @Override
    public Product createProduct() {
        return new Boat();
    }
}
