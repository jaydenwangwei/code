package com.jayden.util;

import java.util.Arrays;

/**
 * 对数器
 * 1、撰写自己的算法
 * 2、实现一个绝对正确但是可以复杂度不好的方法
 * 3、实现一个随机样本产生器
 * 4、将自己的算法和绝对正确的方法比对很多次来验证自己的算法是否正确
 * 5、如果有一个样本使得比对出错，打印样本分析是哪个算法出错
 * 6、当样本数量很多比对时测试依然正确，可以确定自己的方法已经正确
 */
public class LogarithmUtil {
    /**
     * 随机生成数组
     *
     * @param size
     * @param value
     * @return
     */
    public static int[] generateRandomArray(int size, int value) {
        int[] arr = new int[(int) ((size + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((value + 1) * Math.random()) - (int) (value * Math.random());
        }
        return arr;
    }

    /**
     * 系统提供的绝对正确的方法，然后跟自己做的算法进行比对
     *
     * @param arr
     */
    public static void absoluteMethod(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 判断两个数组是否相等
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 深拷贝数组
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }
}
