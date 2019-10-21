package com.vick.designpattern.action.mediator.case2;

import java.util.TimerTask;

public class OneTask extends TimerTask {
    private static int num = 0;

    @Override
    public void run() {
        System.out.println("I'm OneTask " + ++num);
    }
}
