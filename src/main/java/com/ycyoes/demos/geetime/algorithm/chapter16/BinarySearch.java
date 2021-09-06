package com.ycyoes.demos.geetime.algorithm.chapter16;
/**
 * 二分查找变种
 *
 * @author ycyoes
 * @date 2021-09-06
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] a = {1, 2, 2};
        int r = bsearchLast(a, 3, 2);
        System.out.println(r);
    }

    //查找最后一个值等于给定值的元素
    public static int bsearchLast (int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else if(a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == 0) || (mid == n - 1) || (a[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }

        return -1;
    }

    //查找第一个值等于给定值的元素 - 方法二
    public int bsearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    //查找第一个值等于给定值的元素 - 方法一
    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low < n && a[low] == value) return low;
        else return -1;
    }
}
