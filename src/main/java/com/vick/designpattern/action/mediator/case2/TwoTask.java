package com.vick.designpattern.action.mediator.case2;

import java.util.TimerTask;

public class TwoTask extends TimerTask {
    private static int num = 1000;

    @Override
    public void run() {
        System.out.println("I'm TwoTask " + num--);
    }
}
