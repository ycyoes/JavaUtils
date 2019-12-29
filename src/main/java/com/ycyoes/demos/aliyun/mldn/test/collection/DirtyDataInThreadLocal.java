package com.ycyoes.demos.aliyun.mldn.test.collection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//ThreadLocal副作用：产生脏数据和内存泄漏
//1. 线程池会重用Thread对象，与Thread绑定的类的静态属性ThreadLocal变量会被重用，如果不显式调用remove()，
// 那么倘若下一个线程不调用set()设置初始值，就可能get()到重用的线程信息，包括ThreadLocal所关联的线程对象的value值
//2. 内存泄漏：源代码中提示使用static关键字来修饰ThreadLocal，此情景下，寄希望于ThreadLocal对象失去引用后，触发弱引用机制
// 来回收Entry的Value就不现实了
//以上两个问题的解决办法：每次用完ThreadLocal时，必须及时调用remove()方法清理。
public class DirtyDataInThreadLocal {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 2; i++) {
            MyThread mt = new MyThread();
            service.execute(mt);
        }
        service.shutdown();
    }

    private static class MyThread extends Thread {
        private static boolean flag = true;

        @Override
        public void run() {
            if (flag) {
                threadLocal.set(this.getName() + ", session info.");
                flag = false;
            }
            System.out.println(this.getName() + " 线程是 " + threadLocal.get());
            //使用完threadlocal后及时调用remove()清理，否则会产生脏数据
//            threadLocal.remove();
//            flag = true;
        }
    }
}
