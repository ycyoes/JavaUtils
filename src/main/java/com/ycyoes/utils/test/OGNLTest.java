package com.ycyoes.utils.test;

import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;

public class OGNLTest {
    public static void main(String[] args) throws OgnlException {
        //调用对象方法
        Integer hashCode = (Integer) Ognl.getValue("hashCode()", "ycyoes");
        System.out.println("对字符串对象调用hashCode方法得到: " + hashCode);

        //调用类方法
        Double result = (Double) Ognl.getValue("@java.lang.Math.@random()", null);
        System.out.println("调用Math类中的静态方法random,得到：" + result);
    }
}
