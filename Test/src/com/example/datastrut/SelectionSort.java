package com.example.datastrut;

import java.util.Arrays;

/**
 * @ClassName SelectionSort
 * @Author nihui
 * @Date 2019/2/15 15:11
 * @Version 1.0
 * @Description 选择排序
 */
public class SelectionSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);

        for (int i = 0; i < arr.length-1 ; i++) {
            int min = i;
            for (int j = i+1; j < arr.length ; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i!=min){
                int tmp = arr[i];
                arr[i] =arr[min];
                arr[min]=tmp;
            }
        }
        return arr;
    }
}
