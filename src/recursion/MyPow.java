package recursion;

/*
 * n, non-neg
 * S1, x * x * x... x -> for loop, O(n)
 * S2.1 linear recursion, x^(n-1) * n, height = x
 * S2, binary recursion, height = logn
 *          mypow(x, n/2) * mypow(x, n - n/2)
 * S3, 右边如果是偶数，直接reuse 左边结果，奇数，右边用左边的结果 * x
 *      time: O(logn)
 */
public class MyPow {
    public long MyPow(int x, int n) {
        // corner
        if (n == 0) return 1;
        // base
        if (n == 1) return x;
        return MyPow(x, n/2) * MyPow(x, n - n/2);
    }

    public long MyPowOpt(int x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;

        long half = MyPowOpt(x, n/2);
        return n % 2 == 1? half * half * x : half * half;
    }
}
