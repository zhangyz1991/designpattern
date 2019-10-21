package com.vick.designpattern.action.mediator.case2;

import java.util.Timer;

public class TaskInvoker {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new OneTask(), 3000, 1000);
        timer.schedule(new TwoTask(), 3000, 1000);
    }
}
