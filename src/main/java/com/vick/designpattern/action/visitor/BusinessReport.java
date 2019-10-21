package com.vick.designpattern.action.visitor;

import java.util.ArrayList;
import java.util.List;

public class BusinessReport {
    private List<Staff> staffs = new ArrayList<>();

    public BusinessReport() {
        staffs.add(new Manager("经理-A"));
        staffs.add(new Engineer("工程师-A"));
        staffs.add(new Engineer("工程师-B"));
        staffs.add(new Engineer("工程师-C"));
        staffs.add(new Manager("经理-B"));
        staffs.add(new Engineer("工程师-D"));
        staffs.add(new Contract("临时工小张"));
    }

    public void show(Visitor visitor) {
        staffs.stream().forEach(staff -> staff.accept(visitor));
    }
}
