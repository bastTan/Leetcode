package binary_search;

// given API isBadVersion(int), return T/F
// find out the first bad version
// 找分界线
// s1, [r, l],会把分界线夹住
public class LC278_first_bad_version {
    // dummy method
    private boolean isBadVersion(int version) {
        return false;
    }

    public int firstBadVersion(int n) {
        if (n <= 0) return n;
        int left = 1, right = n;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (isBadVersion(mid)) { // 是bad，继续往左找
                right = mid - 1; // right now may not be bad ver anymore
            } else {
                left = mid + 1;
            }
        }
      return left;
        //最后出loop时，【r，l】夹住分接线，
        // 如果最后一次走了r，r是bad ver， then r-1 可能不是bad version，
        // 如果最后一次走了l，l不是bad ver， l+1 一定是bad ver

    }
}
