package binary_search;

import org.junit.Test;

/* sorted, distinct integers, logn */
public class LC35_search_insert_position {
    public int find_first_insertion(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int mid = 0;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // [right, left] when finished
            mid = left + (right - left) / 2;
            if (nums[mid] == target || nums[mid] > target) return mid;
        }
        // no number is >= target
        return nums.length;
    }

    @Test
    public void testTry() {
        System.out.println("hahaha");
    }

}


