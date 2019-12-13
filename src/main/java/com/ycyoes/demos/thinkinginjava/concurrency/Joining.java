package com.ycyoes.demos.thinkinginjava.concurrency;

public class Joining {
    public static void main(String[] args) {
        Sleeper
                sleepy = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("grumpy", 1500);
        Joiner
                dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }
    public void run(){
        try {
            System.out.println("---------join run---------> " + getName());
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted.");
        }
        System.out.println(getName() + " join complete.");
    }
}

class Sleeper extends Thread {
    private int duration;
    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }
    public void run(){
        try {
            System.out.println("-----sleeper run---------> " + getName());
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted. " +
                    " isInterrupted(): " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened.");
    }
}
