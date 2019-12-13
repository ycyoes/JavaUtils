package com.ycyoes.demos.thinkinginjava.concurrency.inherit;

//Inheriting directly from the Thread class.
public class SimpleThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;
    public SimpleThread() {
        //Store the thread name:
        super(Integer.toString(++threadCount));
        start();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new SimpleThread();
    }
    public String toString() {
        return "#" + getName() + "(" + countDown + "). ";
    }
    public void run() {
        while(true) {
            System.out.println(this);
            if (--countDown == 0)
                return;
        }
    }
}
