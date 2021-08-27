package com.ycyoes.demos.geetime.algorithm.chapter13;

/**
 * 计数排序（Counting sort）
 *
 * @author ycyoes
 * @date 2021-08-27
 */
public class CountingSort {

    //计数排序，a是数组，n是数组大小。假设数组中存储的都是非负整数
    public void countingSort(int[] a, int n) {
        if (n <= 1) return;

        //查找数组中数据的范围
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        //申请一个计数数组c,下标大小[0, max]
        int[] c = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            c[i] = 0;
        }

        //计算每个元素的个数，放入c中
        for (int i = 0; i < n; i++) {
            c[a[i]]++;
        }

        //依次累加
        for (int i = 1; i <= max; i++) {
            c[i] = c[i - 1] + c[i];
        }

        //临时数组r,存储排序之后的结果
        int[] r = new int[n];

        //计算排序的关键步骤
        for (int i = n - 1; i >= 0; --i) {
            //找到元素在顺序求和数组中的位置，位置即为排名
            int index = c[a[i]] - 1;
            //索引为排名，值为该元素
            r[index] = a[i];
            //每次将顺需求和数组中，累加值减一
            c[a[i]]--;
        }

        //将结果拷贝给a数组
        for (int i = 0; i < n; i++) {
            a[i] = r[i];
        }

    }
}
