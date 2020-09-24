package com.vick.designpattern.structure.adapter.classadapter;

/**
 * 适配器
 *
 * @author Vick
 * @date 2020/9/24
 */
public class Adapter extends Adaptee implements Target {

    @Override
    public void request() {
        super.innateRequest();
    }
}
