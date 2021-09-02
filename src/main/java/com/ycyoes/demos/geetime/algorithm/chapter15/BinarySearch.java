package com.ycyoes.demos.geetime.algorithm.chapter15;

/**
 * 二分查找
 * 1. 二分查找依赖的是顺序表结构，简单点说就是数组（二分查找算法需要按照下标随机访问元素）。
 * 2. 二分查找针对的是有序数据。
 * 3. 数据量太小不适合二分查找
 * 4. 数据量太大也不适合二分查找
 *
 * @author ycyoes
 * @date 2021-09-01
 */
public class BinarySearch {

    //递归实现二分查找
    public int bcsearch(int[] a, int n, int val) {
        return bsearchInternally(a, 0, n - 1, val);
    }

    private int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) return -1;

        int mid = low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a, mid + 1, high, value);
        } else if (a[mid] > value) {
            return bsearchInternally(a, low, mid - 1, value);
        }

        return -1;
    }

    //二分查找
    public int bsearch(int[] a, int n, int val) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (high - low) / 2;
            if (a[mid] == val) {
                return val;
            } else if (a[mid] < val) {
                low = mid + 1;
            } else if (a[mid] > val) {
                high = mid - 1;
            }
        }

        return -1;
    }
}
