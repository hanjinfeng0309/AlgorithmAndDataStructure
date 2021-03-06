package main.algorithm;

import main.utils.ArrayGenerator;
import main.utils.SortingHelper;

public class SelectionSort {

    private SelectionSort() {}

    public static <E extends Comparable<E>> void sort1(E[] arr) {
        // arr[0...i) 未排序
        for (int i = arr.length - 1; i >= 0; i--) {
            // 查找当前数组的最大值
            int maxIndex = i;
            // arr[i...n) 已排序
            for (int j = 0; j < i; j++) {
                if (arr[j].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            // 若最大值就是arr[i]，则无需进行交换操作
            if (maxIndex == i) {
                continue;
            }
            // 交换arr[i]与arr[maxIndex]，将最大值放到arr[i]的位置上，保证arr[i...n) 已排序
            SortingHelper.swap(arr, i, maxIndex);
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        // arr[0...i) 已排序
        for (int i = 0; i < arr.length - 1; i++) {
            // 查找当前数组的最小值
            int minIndex = i;
            // arr[i...n) 未排序
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // 若最小值就是arr[i]，则无需进行交换操作
            if (minIndex == i) {
                continue;
            }
            // 交换arr[i]与arr[minIndex]，将最小值放到arr[i]的位置上，保证arr[0...i) 已排序
            SortingHelper.swap(arr, i, minIndex);
        }
    }

    public static void main(String[] args) {
//        int[] dataSize = { 10000, 100000 };
//        for (int n : dataSize) {
//            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
//            SortingHelper.sortTest(SelectionSort.class, arr);
//        }
        int n = 10000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SelectionSort.class, "sort1", arr);
    }
}
