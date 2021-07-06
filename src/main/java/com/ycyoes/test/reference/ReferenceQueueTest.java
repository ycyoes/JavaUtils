package com.ycyoes.test.reference;


import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ReferenceQueueTest {
    public static void main(String[] args) {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        //用来存储弱引用的目标对象
        List<WeakReference> weakRefUserList = new ArrayList<>();
        //创建大量的弱引用对象，交给weakRefUserList引用
        for (int i = 0; i < 1000000; i++) {
            Integer id = Integer.valueOf(String.valueOf(Math.round(Math.random() * 1000)));
            WeakReference<User> weakReference = new WeakReference<>(new User(Math.round(Math.random() * 1000)), referenceQueue);
            weakRefUserList.add(weakReference);
        }

        WeakReference weakReference;
        Integer count = 0;

        //处理被回收的弱引用
        while ((weakReference = (WeakReference) referenceQueue.poll()) != null) {
            //虽然弱引用存在，但是引用的目标对象已经为空
            System.out.println("JVM 清理了： " + weakReference + ", 从WeakReference中取出对象值为： " + weakReference.get());
            count++;
        }

        //被回收的弱引用总数
        System.out.println("weakReference中的元素数目为： " + count);

        //在弱引用的目标对象不被清理时，可以引用目标对象
        System.out.println("在不被清理的情况下，可以从WeakReference中取出对象值为： " + new WeakReference<>(new User(Integer.valueOf(String.valueOf(Math.round(Math.random() * 1000))))));
    }
}
