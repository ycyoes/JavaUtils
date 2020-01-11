package com.ycyoes.demos.jvm.feature.chapter3;

/**
 * 此代码演示了两点：
 * 1. 对象可以在被GC时自我拯救。
 * 2. 这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 * @author ycyoes
 * @date 2020-01-11 18:49
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;
    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    /**
     * 执行结果：
     * finalize method executed
     * yes, i am still alive :)
     * no, i am dead: (
     *
     * 代码中有两段完全一样的代码片段，执行结果却是一次逃脱成功，一次失败了。
     * 这是因为任何一个对象的finalize()方法只会被系统自动调用一次，如果对象面临下一次回收，它的finalize()方法不会被
     * 再次执行，因此第二段代码的自救行动失败了。
     *
     * 应尽量避免使用finalize()方法，它的运行代价高昂，不确定性大，无法保证各个对象的调用顺序，如今已被官方明确声明为
     * 不推荐使用的语法。
     */
    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead: (");
        }
        //下面这段代码与上面的完全相同，但是这次自救却失败了
        SAVE_HOOK = null;
        System.gc();
        //因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead: (");
        }
    }
}
