package com.ycyoes.test.jdk8.metaspace;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

/*
 * 继承ClassLoader是为了方便调用defineClass方法，因为该方法的定义为protected
 * -XX:+PrintGCDetails  -XX:+PrintGCDateStamps -XX:-UseCompressedClassPointers -XX:MetaspaceSize=50M
 * -XX:MaxMetaspaceSize=500m -XX:MinMetaspaceExpansion=50M -XX:MaxMetaspaceExpansion=50M -XX:MinMetaspaceFreeRatio=5
 */

public class MetaSpace extends ClassLoader {
    public static void main(String[] args) {
        //类持有
        List<Class<?>> classes = new ArrayList<>();
        //循环1000w次生成1000w个不同的类
        for (int i = 0; i < 10000000; i++) {
            ClassWriter cw = new ClassWriter(0);
            //定义一个类名称为Class{i},它的访问域为public,父类为java.lang.Object,不可实现任何接口
            cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
            //定义构造函数<init>方法
            MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null );
            //第一个指令为加载this
            mw.visitVarInsn(Opcodes.ALOAD, 0);
            //第二个指令为调用父类Object的构造函数
            mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            //第三条指令为return
            mw.visitInsn(Opcodes.RETURN);
            mw.visitMaxs(1,1);
            mw.visitEnd();

            MetaSpace test = new MetaSpace();
            byte[] code = cw.toByteArray();

            //定义类
            Class<?> exampleClass = test.defineClass("Class" + i, code, 0, code.length);
            classes.add(exampleClass);
        }
    }
}
