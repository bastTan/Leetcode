package design;

import java.util.Arrays;


/*
a simple ArrayList implementation.
when array size > capacity, capacity * 2, copy original array to the new array.
 */
public class MyArrayList {
    private static final int DEFAULT_CAPACITY = 1;

    private int[] vals;
    private int capacity = DEFAULT_CAPACITY;
    private int length = 0;

    public MyArrayList() {
        this.vals = new int[this.capacity];
    }
    public int getLength() {
       return this.length;
    }
    // append
    public void add(int val) {
        this.length++;
        ensureCapacity();
        vals[this.length-1] = val;
    }
    public int get(int index) {
        checkIndex(index);
        return this.vals[index];
    }
    public void set(int index, int val) {
        checkIndex(index);
        this.vals[index] = val;
    }
    // set(index, val), then shift the rest
    // insert do not allow appened
    public void insert(int index, int val) {
        checkIndex(index);
        this.length++;
        ensureCapacity();
        int prevVal = this.vals[index];
        int currVal = 0;
        for (int i = index+1; i < this.length; i++) {
            currVal = prevVal;
            prevVal = this.vals[i];
            this.vals[i] = currVal;
        }
        this.vals[index] = val;
    }
    // delete arr[i], move the rest to the left
    public void delete(int index) {
        checkIndex(index);
        int numMoved = this.length - index - 1;
        // if there are shifts
        if (numMoved > 0) {
            System.arraycopy(this.vals, index+1, this.vals, index, numMoved);
        }
        this.length--;
    }

    public void print() {
        System.out.println("===========================================");
        System.out.println("current size = " + this.length + ", " +
                        "current capacity = " + this.capacity);
        if (length == 0) {
            System.out.println("{}");
            return;
        }

        System.out.print('{');
        for (int i = 0; i < this.length; i++) {
            System.out.print(vals[i]);
            if (i < this.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println('}');
    }

    private void ensureCapacity(){
        if (length > capacity) {
            int[] newVals = Arrays.copyOf(this.vals, capacity * 2 );
            this.vals = newVals;
            this.capacity = this.capacity * 2;
        }
    }
    private void checkIndex(int index) {
        if (index > this.length - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of current length " + this.length);
        }
    }

    public static void main(String[] args) {
        MyArrayList mylist = new MyArrayList();
        mylist.print();
        mylist.add(1);
        mylist.add(3);
        mylist.print();
        try {
            System.out.println(mylist.get(1));
            mylist.set(1, 4);
            mylist.print();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        mylist.add(3);
        mylist.print();
        mylist.add(5);
        mylist.add(6);
        mylist.add(9);
        mylist.print();
        mylist.insert(2, 2);
        mylist.print();
        mylist.insert(0, 2);
        mylist.print();
        mylist.insert(4, 8);
        mylist.print();
        mylist.insert(8, 10);
        mylist.print();
        mylist.insert(9, 10);
        mylist.print();
        mylist.add(11);
        mylist.print();
        mylist.delete(1);
        mylist.print();
        while (mylist.getLength() > 1) {
            mylist.delete(0);
        }
        mylist.print();
        mylist.delete(0);
        mylist.print();
        mylist.add(1);
        mylist.print();
    }
}

