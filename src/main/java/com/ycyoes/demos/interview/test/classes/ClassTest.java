package com.ycyoes.demos.interview.test.classes;

class A{}
public class ClassTest extends A {

    public static void hello() {
        System.out.println("hello");
    }

    public void test() {
        //
        //
        // getClass()方法在Object类中被定义为final与native,而子类不能覆盖该方法。
        //
        /**
         *  1. 输出包名+ClassTest
         *  2. 原因在于Java语言中任何类都继承自Object类，
         *  getClass()方法在Object类中被定义为final与native,而子类不能覆盖该方法。
         *  因此，this.getClass()和super.getClass()最终调用的是Object类中的getClass()方法。
         *  3. Object类中的getClass()方法的释义是：返回此Object的运行时类。
         *  4. 代码中实际运行的类时ClassTest而不是A
         *  5. 如何才能在子类得到父类的名字呢？
         *  通过反射机制，使用getClass().getSuperclass().getName()获得
         */
        System.out.println("执行类: " + super.getClass().getName());
        System.out.println("父类:   " + super.getClass().getSuperclass().getName());
    }

    public static void main(String[] args) {
        new ClassTest().test();
        /**
         * 1. java中给任何对象赋值为null都是合法的，null可以被强制转换为任意类型的对象，转换的结果还是null
         *  因此，无法调用对象的方法，但是可以调用类的方法（因为类的方法是不依赖于对象而存在的）
         * 2. ((ClassTest)null)可以把null转换为ClassTest类型的对象，
         * 由于hello是一个静态方法，因此可以直接调用（非静态方法会报错）
         */
        ((ClassTest)null).hello();
    }
}
