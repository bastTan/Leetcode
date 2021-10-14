package Sorting;

import org.junit.Test;

import java.util.Arrays;

// input contains 0, 1, 2
// sort in place
// s1: bucket sort, since we only have three type of input
// s2: quicksort, start keep track of 0, end keep track of 2, p keep track of 1.
// we need n pointers to partition into n parts.
// 维护，0 ~ start 为 zero， start 到 p 为 1， p 到 end 为未知，end 到结束为2
public class LC75_sort_colors {
    private void swap(int[] nums, int x, int y) {
        int val = nums[x];
        nums[x] = nums[y];
        nums[y] = val;
    }
    public void sortColorsBucket(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int zero = 0, one = 0;
        for (int n : nums) {
            if (n == 0) zero++;
            if (n == 1) one++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (zero-- > 0) nums[i] = 0;
            else if (one-- > 0) nums[i] = 1;
            else nums[i] = 2;
        }
    }

    // we need 3 pointers to divide to three parts
    public void sortColorPartition(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int left = 0, p = 0, right = nums.length-1;
        // 00000 1111 ???? 2222
        //      l    p    r
        while (p <= right) {
            if (nums[p] == 0) {
                swap(nums, left, p);
                left++;
                p++;
            } else if (nums[p] == 1) {
                p++;
            } else { // 2
                swap(nums, right, p);
                right--;
            }
        }
    }

    @Test
    public void test1() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        System.out.println(Arrays.toString(nums));
    }
}
