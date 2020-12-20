package com.jayden.sort;

/**
 * 选择排序
 */
public class SelectSort {
    public static void selectSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        // 确定排序范围，将最大值或者最小值推送到最开头
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = i;
            // 交换位置
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap(arr, i, maxIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        selectSort(arr);
        System.out.println(arr);
    }
}
