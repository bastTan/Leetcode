package design;

import java.util.Arrays;


/*
a simple ArrayList implementation.
when array size > capacity, capacity * 2, copy original array to the new array.
 */
public class BastArrayList {
    private static final int DEFAULT_CAPACITY = 1;

    private int[] vals;
    private int capacity = DEFAULT_CAPACITY;
    private int length = 0;

    public BastArrayList() {
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
    // if insert index == length, append
    public void insert(int index, int val) {
        // insert at index == append at the end
        if (index == this.length) {
            add(val);
            return;
        }
        checkIndex(index);
        this.length++;
        ensureCapacity();

        int currVal = val;
        for (int i = index; i < this.length; i++) {
            int tmp = this.vals[i];
            this.vals[i] = currVal;
            currVal = tmp;
        }
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

    @Override
    public String toString() {
        System.out.println("===========================================");
        System.out.println("current size = " + this.length + ", " +
                        "current capacity = " + this.capacity);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.length; i++) {
            sb.append(this.vals[i]);
            if (i < this.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void ensureCapacity(){
        if (length > capacity) {
            int[] newVals = Arrays.copyOf(this.vals, capacity * 2 );
            this.vals = newVals;
            this.capacity *= 2;
        }
    }
    private void checkIndex(int index) {
        if (index > this.length - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of current length " + this.length);
        }
    }

    public static void main(String[] args) {
        BastArrayList mylist = new BastArrayList();
        System.out.println(mylist);
        mylist.add(1);
        mylist.add(3);
        System.out.println(mylist);
        try {
            System.out.println(mylist.get(1));
            mylist.set(1, 4);
            System.out.println(mylist);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        mylist.add(3);
        System.out.println(mylist);
        mylist.add(5);
        System.out.println(mylist);
        mylist.delete(3);
        System.out.println(mylist);
        mylist.add(6);
        mylist.add(9);
        System.out.println(mylist);
        mylist.insert(2, 2);
        System.out.println(mylist);
        mylist.insert(0, 2);
        System.out.println(mylist);
        mylist.insert(4, 8);
        System.out.println(mylist);
        mylist.insert(8, 10);
        System.out.println(mylist);
        mylist.insert(9, 10);
        System.out.println(mylist);
        mylist.add(11);
        System.out.println(mylist);
        mylist.delete(1);
        System.out.println(mylist);
        while (mylist.getLength() > 1) {
            mylist.delete(0);
        }
        System.out.println(mylist);
        mylist.delete(0);
        System.out.println(mylist);
        mylist.add(1);
        System.out.println(mylist);
        System.out.println(mylist);
    }
}

