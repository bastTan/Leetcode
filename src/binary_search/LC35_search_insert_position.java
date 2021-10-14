package binary_search;

import org.junit.Test;

// nums = [1, 3, 4, 6], target = 5
// insertion position will be the smallest number that is greater than 5.
// use s3, left+1 < right, 找分界线。
// target_____target_____target
public class LC35_search_insert_position {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int mid = 0;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid;
            else                         right = mid;
        }

        // target_____target_____target
        if (target <= nums[left]) return left;
        else if (target <= nums[right]) return right;
        return right + 1;
    }

    @Test
    public void testTry() {
        System.out.println("hahaha");
    }

}


