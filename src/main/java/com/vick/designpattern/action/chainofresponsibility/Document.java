package com.vick.designpattern.action.chainofresponsibility;

import lombok.Getter;
import lombok.Setter;

public class Document {
    public static final int PRESIDENT_LEVEL = 0;
    public static final int DEPUTYMANAGER_LEVEL = 1;
    public static final int DIVISIONMANAGER_LEVEL = 2;

    @Getter
    @Setter
    private int level = 0;
    @Getter
    @Setter
    private String name;
}
