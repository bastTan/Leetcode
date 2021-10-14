package Sorting;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public int[] quickSort(int[] array) {
        if (array == null || array.length == 0) return array;
        quickSort(array, 0, array.length-1);
        return array;
    }

    private void quickSort(int[] array, int start, int end) {
        // possibly, start = 0, end = -1, make sure we count that as base case as well
        if (start >= end) return;
        int pivot = findPivotAndSort(array, start, end);
        quickSort(array, start, pivot - 1);
        quickSort(array, pivot+1, end);
    }

    private int findPivotAndSort(int[] array, int start, int end) {
        int pivot = start + new Random().nextInt(end - start + 1);
        int target = array[pivot];

        // push pivot to the end
        swap(array, pivot, end);

        int lt = start, rt = end-1;
        // everything on the left of lt should < target
        // everything on the right of rt should > target
        while (lt <= rt) {
            // keep going until find violation
            if (array[lt] <= target) lt++;
            else if ( array[rt] > target) rt--;
            else {
                swap(array, lt, rt);
            }
        }
        // [rt, lt]
        swap(array, lt, end);
        return lt;
    }

    private void swap(int[] array, int left, int right) {
        int leftVal = array[left];
        array[left] = array[right];
        array[right] = leftVal;
    }

    @Test
    public void testOrder() {
        int[] arr = {7, 7, 3};
        int[] sorted = quickSort(arr);
        System.out.println(Arrays.toString(sorted));
    }
}


