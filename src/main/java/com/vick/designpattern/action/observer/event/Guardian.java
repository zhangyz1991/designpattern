package com.vick.designpattern.action.observer.event;

public class Guardian implements CryEventListener {

    public void askForReason() {
        System.out.println("Ask for information");
    }

    public void droppy(String childName) {
        System.out.println("Dear " + childName + ", please don't cry,and i'll buy you sugar.");
    }

    @Override
    public void fire(CryEvent event) {
        Child child = (Child) event.getSource();
        askForReason();
        droppy(child.getName());
    }
}
