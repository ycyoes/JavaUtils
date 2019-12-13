package com.ycyoes.demos.thinkinginjava.concurrency.inherit;

//A Runnable containing its own driver Thread.
public class SelfManaged implements Runnable {
    private int countDown = 5;
    private Thread t = new Thread(this);
    public SelfManaged() { t.start(); }
    public String toString() {
        return Thread.currentThread().getName() +
                "(" + countDown + "). ";
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++)
            new SelfManaged();
    }
    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0)
                return;
        }
    }
}
