package com.ycyoes.demos.aliyun.mldn.concurrency.lock;

public class DeadLock implements Runnable {
    private Jian ji = new Jian();
    private XiaoQiang xq = new XiaoQiang();
    public void run() {
        ji.say(xq);
    }
    public DeadLock(){
        new Thread(this).start();
        xq.say(ji);
    }

    public static void main(String[] args) {
        new DeadLock();
    }
}

class Jian {
    public synchronized void say(XiaoQiang xq) {
        System.out.println("阿健说: 此路是我开，要想从此过，给我10块钱");
        xq.get();
    }
    public synchronized void get() {
        System.out.println("阿健说:得到10块钱，可以买饭吃了");
    }
}

class XiaoQiang {
    public synchronized void say(Jian ji) {
        System.out.println("小强说，让我过再给钱");
        ji.get();
    }
    public synchronized void get() {
        System.out.println("小强说：逃过一劫，可以继续送快餐了。");
    }

}
