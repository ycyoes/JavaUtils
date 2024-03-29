package com.ycyoes.demos.geetime.algorithm.chapter12;

/**
 * 归并排序
 *
 * @author ycyoes
 * @date 2021-08-25 18:12
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {8, 10, 2, 3, 6, 1, 5};
        quickSort(a, 7);
        for (int i = 0; i < 7; i++) {
            System.out.println(a[i]);
        }
    }
    //快速排序，a是数组，n表示数组的大小
    public static void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n-1);
    }

    //快速排序递归函数，p/r为下标
    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r); //获取分区点
        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q+1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (a[j] < pivot) {
                if (i == j) {
                    ++i;
                } else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
        System.out.println("i=" + i);
        return i;
    }
}
