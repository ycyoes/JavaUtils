package com.ycyoes.demos.interview.test.init;

public class VarTest {

    public static void main(String[] args) {
        modify();
    }
    public static void modify(){
        int i, j = 2, k = 0;
//        int i, j, k;    //j,k未初始化，报错
        i = 100_234;
        while(i > 0) {
            i = j * 2;
            System.out.println("The value of j is: " + j);
            k = k + 1;
            i--;
        }
    }

}
