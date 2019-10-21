package com.vick.designpattern.action.observer.event;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private String name;
    private List<CryEventListener> listeners = new ArrayList<>();

    public Child(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addListener(CryEventListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(CryEventListener listener) {
        this.listeners.remove(listener);
    }

    public void cry() {
        System.out.println(this.name + " starts to cry.....");
        notifyListeners();
    }

    public void notifyListeners() {
        listeners.stream().forEach(listener -> listener.fire(new CryEvent(this)));
    }


}
