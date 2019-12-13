package com.ycyoes.demos.thinkinginjava.concurrency.task;

/**
 * Demonstration of the Runnable interface
 * @author ycyoes
 * @since 2019-12-12 16:56
 * @version 1.0
 */
public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff() {}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!" ) + "). ";
    }

    //当从Runnable导出一个类时，它必须具有run()方法，但是这个方法并无特殊之处---它不会产生任何内在的线程能力。
    //要实现线程行为，你必须显式的将一个任务附着到线程上。
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            //将CPU从一个线程转移给另一个线程
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
