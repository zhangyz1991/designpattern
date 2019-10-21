package com.vick.designpattern.action.observer.jdkobserver;

public class ObserverInvoker {

    public static void main(String[] args) {
        Clown clown = new Clown();
        for (int i = 0; i < 20; i++) {
            Viewer viewer = new Viewer(i);
            clown.addObserver(viewer);
            System.out.println("The audience with seat number " + i + " seats");
        }
        clown.perform();
        clown.exit();
    }
}
