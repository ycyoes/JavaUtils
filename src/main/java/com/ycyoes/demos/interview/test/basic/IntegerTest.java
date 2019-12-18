package com.ycyoes.demos.interview.test.basic;


public class IntegerTest {
    public static void main(String[] args) {
        objPoolTest();
    }

    /**
     * 声明Integer对象时，JVM首先会在Integer对象的缓存池中查找有无值为40的对象，
     * 如果有直接返回该对象的引用；如果没有，则使用new keyword创建一个对象，并返回该对象的引用地址。
     * Java中==比较的是两个对象是否是同一个引用（即比较内存地址），i2和i2都是引用的同一个对象，So i1==i2结果为”true“；
     * 使用new方式创建的i4和i5,虽然他们的值相等，但是每次都会重新创建新的Integer对象，不会被放入到对象池中，所以他们不是同一个引用
     */
    public static void objPoolTest() {
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);
        System.out.println("i1=i2\t" + (i1 == i2)); //true
        //对于i1==i2+i3、i4==i5+i6结果为True，是因为，Java的数学计算是在内存栈里操作的，
        // Java会对i5、i6进行拆箱操作，其实比较的是基本类型（40=40+0），他们的值相同，因此结果为True。
        System.out.println("i1=i2+i3\t" + (i1 == i2 + i3)); //true
        System.out.println("i4=i5\t" + (i4 == i5)); //false
        System.out.println("i1=i2+i3\t" + (i4 == i5 + i6)); //true

        //Integer i7=400，Integer i8=400他们的值已经超出了常量池的范围，
        // JVM会对i7和i8各自创建新的对象（即Integer i7=new Integer(400)），所以他们不是同一个引用。
        System.out.println("---------------------------------");
        Integer i7 = 400;
        Integer i8 = 400;
        Integer i9 = 0;
        Integer i10 = new Integer(400);
        Integer i11 = new Integer(400);
        Integer i12 = new Integer(0);
        System.out.println("i7=i8\t" + (i7 == i8)); //true
        System.out.println("i7=i7+i8\t" + (i7 == i7 + i8)); //true
        System.out.println("i10=i11\t" + (i10 == i11)); //false
        System.out.println("i10=i11+i12\t" + (i4 == i10 + i12)); //true
    }
}
