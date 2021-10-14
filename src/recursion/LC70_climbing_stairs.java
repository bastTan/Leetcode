package recursion;

import org.junit.Test;

// S1, recursion,
// time: O(2^n), space O(n)
// S2, recursion with memorization
// time: O(n), space O(n) -> mem array, + stack
// S3, DP
// time: O(n), space O(n), mem array
// S4, DP + only two value to mem, instead of array
// time: O(n), space O(1)
public class LC70_climbing_stairs {
    // S2 recursion with mem
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int mem[] = new int[n+1];
        mem[1] = 1;
        mem[2] = 2;
        return climbStairs(n, mem);
    }
    private int climbStairs(int n, int[] mem) {
        if (mem[n] > 0) return mem[n];
        mem[n] =  climbStairs(n - 1, mem) + climbStairs(n - 2, mem);
        return mem[n];
    }

    // S3 DP
    public int climbStairsDP(int n) {
        if (n <= 2) return n;
        int dp[] = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // S4 DP with only two value to save previous
    public int climbStarisDP2(int n) {
        if (n <= 2) return n;
        int step1 = 1;
        int step2 = 2;
        for (int i = 3; i <= n; i++) {
            int val = step1 + step2;
            step1 = step2;
            step2 = val;
        }
        return step2;
    }

    @Test
    public void test() {
        System.out.println(climbStairs(3));
    }
}
