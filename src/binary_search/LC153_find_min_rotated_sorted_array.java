package binary_search;

// no dup
// 1. if num[left] < num[right]  -> sorted, num[0]
// 2. rotated 后，如果左边单调递增，最小一定在右边
//    如果左边不是单调递增，最小一定在左边。
// S3, LEFT + 1 < RIGHT, 找分界线
// 左右不断靠近低点，最后取左后右的最小值就好。

// follow up: LC154 has dup
// two condition, if num[left] == mid, left++, if num[right] == mid,right--;

public class LC153_find_min_rotated_sorted_array {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
        return -1;
        int left = 0;
        int right = nums.length - 1;

        // only 1 elem, or already sorted in order
        if (nums[left] <= nums[right]) return nums[left];

        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[left] < nums[right]? nums[left] : nums[right];
    }
}
