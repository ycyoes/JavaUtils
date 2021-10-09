package com.ycyoes.test.matlab;

import com.mathworks.toolbox.javabuilder.*;
import drawplot.Class1;

public class Test {
    public static void main(String[] args) {
        MWNumericArray x = null;    //存放x值的数组
        MWNumericArray y = null;    //存放y值的数组
        Class1 plotter = null;
        int n = 20; //作图点数

        try {
            int[] dims = {1, n};
            x = MWNumericArray.newInstance(dims, MWClassID.DOUBLE, MWComplexity.REAL);
            y = MWNumericArray.newInstance(dims, MWClassID.DOUBLE, MWComplexity.REAL);

            //定义y = x ^ 2
            for (int i = 1; i <= n; i++) {
                x.set(i, i);
                y.set(i, i * i);
            }

            //初始化plotter对象
            plotter = new Class1();

            //作图
            plotter.drawplot(x ,y);
            plotter.waitForFigures();
        } catch (MWException e) {
            e.printStackTrace();
        } finally {
            //释放本地资源
            MWArray.disposeArray(x);
            MWArray.disposeArray(y);
            if (plotter != null) {
                plotter.dispose();
            }
        }
    }
}
