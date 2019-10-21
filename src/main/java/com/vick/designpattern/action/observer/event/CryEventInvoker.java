package com.vick.designpattern.action.observer.event;

public class CryEventInvoker {

    public static void main(String[] args) {
        Child child = new Child("Dan");
        Guardian grandpa = new Guardian();
        child.addListener(grandpa);
        child.cry();
    }
}
