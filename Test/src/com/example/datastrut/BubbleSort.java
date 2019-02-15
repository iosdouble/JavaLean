package com.example.datastrut;

import java.util.Arrays;

/**
 * @ClassName BubbleSort
 * @Author nihui
 * @Date 2019/2/15 15:06
 * @Version 1.0
 * @Description 冒泡排序
 */
public class BubbleSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);

        for (int i = 0; i <arr.length ; i++) {
            //设置一个标记，如果没有发生交换的话就直接退出，表示待排序的序列已经有序
            boolean flag = true;
            for (int j = 0; j < arr.length-1;j++) {
                if (arr[j]<arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = false;
                }
            }
            if (flag){
                break;
            }
        }
        return arr;
    }
}
