package com.ycyoes.demos.interview.test.circle;

//for(;;)与while(true)，反编译后内容一样
//结论：Java中无限循环二者均可，视个人喜好；其他语言开发者是具体情况而定（c/c++中for会进行优化而
// while（1）不会，故而使用for较多）
public class ForWhileTest {
    public static void main(String[] args) {
       forTest();
       whileTest();
    }

    public static void whileTest() {
        while(true) {
//            System.out.println("while");
        }
    }
    public static void forTest() {
        for(;;) {
//            System.out.println("for");
        }
    }
}
