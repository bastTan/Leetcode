package binary_search;

// call binary_search twice
// find first occ, move left even target is found, post process, check left, then check right, could be not found
// find last occ, move right even target is found, post process, check right, then check left, could be not found
public class LC34_find_first_and_last {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int[] result = new int[2];
        result[0] = findLeftIndex(nums, target);
        result[1] = findRightIndex(nums,target);

        return result;
    }
    // find first, move left even target is found
    private int findLeftIndex(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (target <= nums[mid]) right = mid;
            else if (target > nums[mid]) left = mid;
        }

        if (nums[left] == target) return left;
        return nums[right] == target? right : -1;
    }

    // to find last, we move right even we found target
    private int findRightIndex(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (target < nums[mid]) right = mid;
            else if (target >= nums[mid]) left = mid;
        }

        if (nums[right] == target) return right;
        return nums[left] == target? left : -1;

    }
}
