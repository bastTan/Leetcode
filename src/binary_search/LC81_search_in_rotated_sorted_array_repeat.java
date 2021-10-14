package binary_search;

// rotated sorted array
// has dup
// nums = [2, 5, 6, 0, 0, 1, 2], target = 0
// return true;
// 1. 用nums[left] < nums[mid] 判断单调区间时，
// 多了case == , 直接移动左指针
// 2. 进入单调区间后，判断target在哪一段时， >= not >
public class LC81_search_in_rotated_sorted_array_repeat {
   public boolean search(int[] num, int target)  {
       if (num == null || num.length == 0) return false;

       int left = 0, right = num.length - 1;
       int mid = 0;
       while (left <= right) {
           mid = left + (right - left) / 2;
           if (num[mid] == target) return true;

           if (num[left] < num[mid]) {
               if (target >= num[left] && target < num[mid]) {
                   right = mid - 1;
               } else {
                   left = mid + 1;
               }
               // right half in order
           } else if (num[left] > num[mid]) {
               if (target > num[mid] && target <= num[right] ) {
                   left  = mid + 1;
               } else {
                   right = mid - 1;
               }
           } else { // num[left] == mid, simply move pointer to the right
               left++;
           }
       }
       return false;
   }
}
