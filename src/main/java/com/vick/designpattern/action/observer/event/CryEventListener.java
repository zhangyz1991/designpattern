package com.vick.designpattern.action.observer.event;

import java.util.EventListener;

public interface CryEventListener extends EventListener {
    void fire(CryEvent event);
}
