package com.vick.designpattern.creation.factory;

interface Product {

    String getProductName();

}

public class SimpleFactoryInvoker {

    public static void main(String[] args) {
        Product productA = SimpleFactory.createProduct("Plane");
        System.out.println(productA.getProductName());

        Product productB = SimpleFactory.createProduct("Boat");
        System.out.println(productB.getProductName());
    }

}

class SimpleFactory {

    public static Product createProduct(String productType) {
        Product product = null;
        switch (productType) {
            case "Plane":
                product = new Plane();
                break;
            case "Boat":
                product = new Boat();
                break;
            default:
        }
        return product;
    }
}

class Plane implements Product {

    @Override
    public String getProductName() {
        return "Plane";
    }
}

class Boat implements Product {

    @Override
    public String getProductName() {
        return "Boat";
    }
}
