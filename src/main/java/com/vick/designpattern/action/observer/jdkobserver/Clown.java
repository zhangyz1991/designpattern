package com.vick.designpattern.action.observer.jdkobserver;

import java.util.Observable;
import java.util.Random;

public class Clown extends Observable {
    public static final int PERFORM_GOOD = 0;
    public static final int PERFORM_BAD = 1;
    public static final int PERFORM_COMPLETE = 2;

    public void perform() {
        System.out.println("The clown start acting.");
        int random = new Random().nextInt(2);
        switch (random) {
            case PERFORM_GOOD:
                System.out.println("The clown performed well");
                break;
            case PERFORM_BAD:
                System.out.println("The clown performed badly");
                break;
        }
        setChanged();
        notifyObservers(random);
    }

    public void exit() {
        System.out.println("The performance ends and the clown exits");
        setChanged();
        notifyObservers(PERFORM_COMPLETE);
    }
}
