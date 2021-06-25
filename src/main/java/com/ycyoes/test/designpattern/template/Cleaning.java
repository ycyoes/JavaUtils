package com.ycyoes.test.designpattern.template;

public abstract class Cleaning {

    public void clean() {
        prepare();
        implement();
        windup();
        report();
    }

    abstract void prepare();
    abstract void implement();
    abstract void windup();
    void report() {
        System.out.println("告诉别人已经打扫完成。");
    }
}
