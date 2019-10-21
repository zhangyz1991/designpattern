package com.vick.designpattern.structure.flyweight;

public abstract class Flyweight {
    private String intrinsic;

    private String extrinsic;

    public Flyweight(String extrinsic) {
        this.extrinsic = extrinsic;
    }

    public abstract void operate(int extrinsic);

    public String getIntrinsic() {
        return intrinsic;
    }

    public void setIntrinsic(String intrinsic) {
        this.intrinsic = intrinsic;
    }
}
