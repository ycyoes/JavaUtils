package com.ycyoes.test.designpattern.template;

public class WipeBlackboard extends Cleaning {

    @Override
    void prepare() {
        System.out.println("找到黑板擦。");
    }

    @Override
    void implement() {
        System.out.println("用力擦黑板。");
    }

    @Override
    void windup() {
        System.out.println("清理粉笔屑。");
    }
}
