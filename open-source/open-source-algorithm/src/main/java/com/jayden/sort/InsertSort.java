package com.jayden.sort;

/**
 * 插入排序
 */
public class InsertSort {
    public static void insertSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        // 确定排序范围,0到i-1的位置已经有序了，解决的就是第i个位置的往哪里插入
        for (int i = 1; i < arr.length; i++) {
            // 交换位置
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }

        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 2, 1};
        insertSort(arr);
        System.out.println(arr);
    }
}
