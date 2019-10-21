package com.vick.designpattern.action.observer.event;

import java.util.EventObject;

public class CryEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CryEvent(Object source) {
        super(source);
    }
}
