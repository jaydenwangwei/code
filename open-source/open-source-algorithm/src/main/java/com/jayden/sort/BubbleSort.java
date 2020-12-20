package com.jayden.sort;

import com.jayden.util.LogarithmUtil;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        // 确定排序范围,将最大值或者最小值推送到末尾
        for (int end = arr.length - 1; end > 0; end--) {
            // 交换位置
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
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
        int testCount = 500000;
        int size = 100;
        int value = 1000;
        boolean succeed = true;
        for (int i = 0; i < testCount; i++) {
            int[] arr1 = LogarithmUtil.generateRandomArray(size, value);
            int[] arr2 = LogarithmUtil.copyArray(arr1);
            bubbleSort(arr1);
            LogarithmUtil.absoluteMethod(arr2);
            if (!LogarithmUtil.isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice" : "Wrong");
    }
}
