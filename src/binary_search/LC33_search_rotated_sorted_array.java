package binary_search;

/*
 * rotated binary search, no repeat
 * input: nums = [4, 5, 6, 7, 0, 1, 2], target = 0
 * output: 4 (nums[4] is 0)
 */
public class LC33_search_rotated_sorted_array {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        // base case
        if (nums.length == 0) return -1;
        if (nums.length == 1) {
            if (nums[0] == target) return 0;
            else                 return -1;
        }

        int left = 0, right = nums.length-1;
        int mid = 0;

        while (left <= right) {
            mid = left + (right - left)  / 2;

            if (nums[mid] == target) return mid;
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;

            if (nums[left] < nums[mid]) { // right order
                if (target > nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // reverse order, the part from mid to the end is the right order for sure
                if (target > nums[mid] && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

