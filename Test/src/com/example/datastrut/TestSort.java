package com.example.datastrut;

import java.nio.channels.Selector;

/**
 * @ClassName TestSort
 * @Author nihui
 * @Date 2019/2/15 15:50
 * @Version 1.0
 * @Description TODO
 */
public class TestSort {
    public static void main(String[] args) throws Exception {
        int[] array = {1,23,34,5,54,56,3,12};

        //实现冒泡排序
        BubbleSort bubbleSort = new BubbleSort();
        printSort("冒泡排序结果：",bubbleSort.sort(array));

        //实现快速排序
        QuickSort quickSort = new QuickSort();
        printSort("快速排序结果：",quickSort.sort(array));

        //选择排序结果
        SelectionSort selectionSort = new SelectionSort();
        printSort("选择排序结果：",selectionSort.sort(array));

        //插入排序结果
        InsertSort insertSort = new InsertSort();
        printSort("插入排序结果：",insertSort.sort(array));

        //希尔排序结果
        ShellSort shellSort = new ShellSort();
        printSort("希尔排序结果：",shellSort.sort(array));

        //归并排序结果
        MergeSort mergeSort = new MergeSort();
        printSort("归并排序结果：",mergeSort.sort(array));

        //堆排序结果
        HeapSort heapSort = new HeapSort();
        printSort("堆排序结果：",heapSort.sort(array));

        //计数排序结果
        CountingSort countingSort = new CountingSort();
        printSort("计数排序结果：",countingSort.sort(array));

        //桶排序
        BucketSort bucketSort = new BucketSort();
        printSort("桶排序结果：",bubbleSort.sort(array));

        //基数排序
        RadixSort radixSort = new RadixSort();
        printSort("基数排序结果：",radixSort.sort(array));
    }

    private static void printSort(String name,int[] array) {
        System.out.println(name);
        for(int i:array){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
