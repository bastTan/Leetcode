package binary_search;

// compare mid and x/mid, prevent overflow
// use s1, left <= right. (find value, not boundary)
// return right at the end, get the floor, [right, left] is the ending status
public class LC69_sqrt {
    public int mySqrt(int x) {
        if (x < 2) return x;

        // max will be x/2;
        int mid, left = 2, right = x / 2;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (mid == (x / mid)) {
                return mid;
            } else if (mid > (x / mid)) {
                // too big, move right
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // after loop, no exact target found. we get the floor of the sqrt(x)
        // [right, left]
        return right;
    }
}
