package binary_search;

/*
 * num = 121356, return peak index, can have multiple peak, just return 1
 */
public class LC162_find_peak_elem {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            // has at least 3 elem, to enter the loop
            // 132 -> 3 比两边高
            if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) return mid;
            if (nums[mid] < nums[mid+1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[left] < nums[right] ? right : left;
    }
}
