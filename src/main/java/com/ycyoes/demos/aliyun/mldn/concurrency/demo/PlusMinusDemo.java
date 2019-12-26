package com.ycyoes.demos.aliyun.mldn.concurrency.demo;
//设计4个线程对象，两个线程执行减操作，两个线程执行加操作(0, 1循环输出)
//加减线程若无演示输出不对
public class PlusMinusDemo {
    public static void main(String[] args) {
        Resource res = new Resource();
        AddThread at = new AddThread(res);
        SubThread st = new SubThread(res);
        new Thread(at, "加法线程 - A").start();
        new Thread(at, "加法线程 - B").start();
        new Thread(st, "减法线程 - X").start();
        new Thread(st, "减法线程 - Y").start();
    }
}

class SubThread implements Runnable {
    private Resource resource;
    public SubThread(Resource resource) {
        this.resource = resource;
    }
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                this.resource.sub();
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class AddThread implements Runnable {
    private Resource resource;
    public AddThread(Resource resource) {
        this.resource = resource;
    }
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                this.resource.add();
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Resource {    //定义一个操作的资源
    private int num = 0;    //这个要进行加减操作的数据
    //加减的切换
    private boolean flag = true;  //true: 可进行加法操作 false: 可进行减法操作
    public synchronized void add() throws Exception{    //执行加法操作
        System.out.println("加法 - flag: " + this.flag);
        if (!this.flag) {
            super.wait();
        }
        Thread.sleep(100);
        this.num++;
        System.out.println("加法操作 - " + Thread.currentThread().getName() + ", num = " + this.num);
        this.flag = false;
        super.notifyAll();
    }
    public synchronized void sub()  throws Exception {    //执行减法操作
        System.out.println("减法- flag: " + this.flag);
        if (this.flag) {
            super.wait();
        }
        Thread.sleep(200);
        this.num--;
        System.out.println("减法操作 - " + Thread.currentThread().getName() + ", num = " + this.num);
        this.flag = true;
        super.notifyAll();
    }
}