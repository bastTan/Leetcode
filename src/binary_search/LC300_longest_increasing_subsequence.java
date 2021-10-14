package binary_search;

import java.util.Arrays;

/*
 * increasing subsequence, 不用是连续的， 中间可以跳过elem，只要一直在增加就可以
 * i.e 1 4 3 5 6 3 8  -- 1 3 5 6
 * s1. dp, O(n^2)
 * s2. dp with better result, 1 2 8 5 -> 1 2 5 m比 1 2 8 好。 因为比5比8更有可能接上更长的序列
 * 在当前数不能给序列+1时，去sub sequence 找第一个比当前num大的值换掉，
 * 虽然subsequence没+1，但是得到了同等长度更好的subseq。
 * s3. S2 + BINARY SEARCH. 要给5 找位置的时候可以用上binary search。 变nlogn
 */
public class LC300_longest_increasing_subsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 1;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j=0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        maxLength = Math.max(maxLength, dp[i]);
                    }
                }
            }
        }
        return maxLength;
    }
}
