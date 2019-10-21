package com.vick.designpattern.creation.factory;

interface Mouse {
    void sayHi();
}

interface Keyboard {
    void sayHi();
}

public class AbstractFactoryInvoker {

    public static void main(String[] args) {
        AbstractFactory lenovoFactory = new LenovoFactory();
        Keyboard lenovoKeyBoard = lenovoFactory.createKeyBoard();
        Mouse lenovoMouse = lenovoFactory.createMouse();
        lenovoKeyBoard.sayHi();
        lenovoMouse.sayHi();
    }
}

abstract class AbstractFactory {
    abstract Mouse createMouse();

    abstract Keyboard createKeyBoard();
}

class LenovoMouse implements Mouse {

    @Override
    public void sayHi() {
        System.out.println("LenovoMouse");
    }
}

class HuaWeiMouse implements Mouse {

    @Override
    public void sayHi() {
        System.out.println("HuaWeiMouse");
    }
}

class LenovoKeyBoard implements Keyboard {
    @Override
    public void sayHi() {
        System.out.println("LenovoKeyBoard");
    }
}

class HuaWeiKeyBoard implements Keyboard {

    @Override
    public void sayHi() {
        System.out.println("HuaWeiKeyBoard");
    }
}

class LenovoFactory extends AbstractFactory {

    @Override
    Mouse createMouse() {
        return new LenovoMouse();
    }

    @Override
    Keyboard createKeyBoard() {
        return new LenovoKeyBoard();
    }
}

class HuaWeiFactory extends AbstractFactory {

    @Override
    Mouse createMouse() {
        return new HuaWeiMouse();
    }

    @Override
    Keyboard createKeyBoard() {
        return new HuaWeiKeyBoard();
    }
}
