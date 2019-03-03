package com.example.datastrut;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Author nihui
 * @Date 2019/2/14 13:59
 * @Version 1.0
 * @Description 实现快速排序算法
 *
 */
public class QuickSort implements IArraySort{
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        //
        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);
        return quickSort(arr,0,arr.length-1);
    }

    private int[] quickSort(int[] arr, int left, int right){
        if (left<right){
            int partionIndex = partition(arr,left,right);
            quickSort(arr,left,partionIndex-1);
            quickSort(arr,partionIndex+1,right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = left;
        int index = pivot+1;
        for (int i = index; i <=right ; i++) {
            if (arr[i]<arr[pivot]){
                swap(arr,i,index);
                index++;
            }
        }
        swap(arr,pivot,index-1);
        return index-1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
