package com.vick.designpattern.action.observer.jdkobserver;

import java.util.Observable;
import java.util.Observer;

public class Viewer implements Observer {
    private int seatNo;

    public Viewer(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getSeatNo() {
        return seatNo;
    }

    @Override
    public void update(Observable o, Object arg) {
        Integer state = (Integer) arg;
        switch (state) {
            case Clown.PERFORM_GOOD:
                applause();
                break;
            case Clown.PERFORM_BAD:
                boo();
                break;
            case Clown.PERFORM_COMPLETE:
                exit();
                break;
        }
    }

    public void applause() {
        System.out.println("The audience with seat number " + getSeatNo() + " applauded");
    }

    public void boo() {
        System.out.println("The audience with seat number " + getSeatNo() + " booed");
    }

    public void exit() {
        System.out.println("The audience with seat number " + getSeatNo() + " exited");
    }
}
