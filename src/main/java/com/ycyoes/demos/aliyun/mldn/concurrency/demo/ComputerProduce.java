package com.ycyoes.demos.aliyun.mldn.concurrency.demo;

//设计一个生产电脑和搬运电脑类，要求生产出一台电脑就搬走一台电脑，如果没有新的电脑生产，则搬运工等待电脑产出；
//如果生产出的电脑没有搬走，则要等待电脑搬走之后再生产，并统计出生产的电脑数量。
public class ComputerProduce {
    public static void main(String[] args) {
        Resources res = new Resources();
        new Thread(new Producers(res)).start();
        new Thread(new Consumers(res)).start();
    }
}

class Consumers implements Runnable {
    private Resources resources;
    public Consumers(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                this.resources.get();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Producers implements Runnable {
    private Resources resources;
    public Producers(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                this.resources.make();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Computer {
    private static int count = 0;  //生产的个数
    private String name;
    private double price;
    public Computer(String name, double price) {
        this.name = name;
        this.price = price;
        count++;
    }
    public String toString() {
        return "第" + count + "台电脑，电脑名字" + this.name + ", 价值： " + this.price;
    }
}

class Resources {
    private Computer computer;
    public synchronized void make() throws Exception {
        if (this.computer != null) {
            super.wait();
        }
        Thread.sleep(100);
        this.computer = new Computer("Lenovo", 1.1);
        System.out.println("生产 " + this.computer);
        super.notifyAll();
    }
    public synchronized void get()  throws Exception {
        if (this.computer == null) {
            super.wait();
        }
        Thread.sleep(10);
        System.out.println("取走 " + this.computer);
        this.computer = null;
        super.notifyAll();
    }
}