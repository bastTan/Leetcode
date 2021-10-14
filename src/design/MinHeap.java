package design;

import java.util.ArrayList;
import java.util.List;

/*
[extractMin]    swap arr[0], arr[last], heapify top till down
[add]           append bottom, heapify bottom till top
heapify(array)  from last parent node, do top down heapify(node) for each node from right to left;
 */
public class MinHeap {
    private int curSize;

    private ArrayList<Integer> array;
    public MinHeap(){
        this.curSize = 0;
        this.array = new ArrayList<>();
    }

    public MinHeap(int[] input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        this.curSize = input.length;

        this.array = new ArrayList<>(input.length);
        for (int val : input) {
            array.add(val);
        }
        buildHeap();
    }
    public int getMin(){
        if (this.curSize < 1) {
            throw new IllegalArgumentException("Out of Boundary");
        }
        return this.array.get(0);
    }
    /*
     1. swap arr[0], arr[length - 1];
     2. heapify from arr[0]
     */
    public int extractMin(){
        if (this.curSize < 1) {
            throw new IllegalArgumentException("Out of Boundary");
        }
        int min = array.get(0);
        swap(0, --this.curSize);
        minHeapify(0);
        return min;
    }
    /*
    append to the end
    then heapify from bottom up, till reach the top or break when already valid.
    always compare to its parent, if smaller than parent, swap
     */
    public void add(int val){
        this.curSize++;
        array.add(val);

        int curr = curSize-1;
        while (curr >= 0) {
            int p = parent(curr);
            if (p >= 0 && array.get(curr) < array.get(p)) {
                swap(curr, p);
                curr = p;
            } else {
                break;
            }
        }
    }

    private int parent(int ind) {
        return (ind - 1) / 2;
    }

    private int leftChild(int ind) {
        return 2 * ind + 1;
    }
    private int rightChild(int ind) {
        return 2 * ind + 2;
    }
    public int size(){
        return this.curSize;
    }

    // convert array to a heap
    // start from the first parent node, bottom up, heapify each of them
    // O(N) not Onlogn, by math, 等比数列
    private void buildHeap() {
        int len = this.curSize / 2;
        for (int i = len; i >= 0 ; i--) {
            minHeapify(i);
        }
    }

    // top to down
    private void minHeapify(int index) {
        // 一直做到底
        // 找curr，leftChild，rightChild 最小。
        // 和最小换。
        // 从之前最小的位置继续往下找
        int curr = index;
        while (curr < curSize) {
            int l = leftChild(curr);
            int r = rightChild(curr);

            int minIndex = curr;
            if (l < curSize && array.get(l) < array.get(minIndex)) {
                minIndex = l;
            }
            if (r < curSize && array.get(r) < array.get(minIndex)) {
                minIndex = r;
            }
            if (minIndex != curr) {
                swap(minIndex, curr);
                curr = minIndex;
            } else {
                break;
            }
        }
    }
    private void swap(int l, int r) {
        int tmp = array.get(l);
        array.set(l, array.get(r));
        array.set(r, tmp);
    }

    @Override
    public String toString() {
       return this.array.subList(0, curSize).toString();
    }

    public static void main(String[] args) {
        int[] input = {9, 10, 9, 2, 3, 4, 5, 8, 1, 2};
        MinHeap minHeap = new MinHeap(input);
        System.out.println(minHeap);
        minHeap.add(0);
        System.out.println(minHeap);
        minHeap.extractMin();
        System.out.println(minHeap);

    }

}
