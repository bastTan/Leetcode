package Others;

import java.util.*;

// LC01: Two sum

// clarification
// no result, return empty list
// not sorted
// no duplicate, no negative
// out requirement?? print order?
// example
/*
target = 7
int[] nums = {2, 3, 4, 5, 8};
              0. 1. 2. 3. 4
*/

class LC01_two_sum {

    public List<List<Integer>> twoSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        Map<Integer, List<Integer>> map = new HashMap<>();
        // (3 ->  1, 2, 6)

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                for (int index : map.get(complement)) {
                    res.add(Arrays.asList(index, i));
                }
            }

            // map.put(nums[i], i);
            map.putIfAbsent(nums[i], new ArrayList<Integer>());
            map.get(nums[i]).add(i);
        }

        return res;
    }

    // var1: sorted, no duplication,
    // left right pointer,
    // 衍生：if dup， then when found target and continue moving,
    // we can only move left
    public List<List<Integer>> twoSumSorted(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        int l = 0, r = nums.length-1;
        while (l < r) {
            int currSum = nums[l] + nums[r];
            if (currSum == target) res.add(Arrays.asList(l,r));
            if (currSum < target) l++;
            else r--;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5, 8};
        int target = 7;
        LC01_two_sum sol = new LC01_two_sum();
        System.out.println(sol.twoSumSorted(nums, target));

    }
}

