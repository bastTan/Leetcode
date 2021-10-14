package binary_search;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// ascending sorted array
// find k closest elements to the target x
// 0 < k < arr.length
// closest meaning: abs diff is bigger, or number is smaller
public class LC658_find_k_closest_element {
    public List<Integer>  findClosestElements(int[] arr, int k, int x) {
        // linkedlist for easy insert/append
        List<Integer> result = new LinkedList<>();

        if (arr == null || arr.length == 0) return result;

        k = Math.min(k, arr.length);
        // find target first
        // Arrays.binarySearch
        int pos = findClosest(arr, x);
        System.out.println("found closest pos : " + pos + " for num " + x);
        result.add(arr[pos]);
        addRemaining(result, arr, pos, k-1, x);
        return result;
    }

    // use binary search
    // if target not found, find the closest
    private int findClosest(int[] arr, int target) {
        // corner case
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // if left diff > right diff, return left
        // if left diff == right diff, return left
        return Math.abs(arr[left] - target) <= Math.abs(arr[right] - target)? left: right;
    }

    private void addRemaining(List<Integer> result, int[] arr, int pos, int count, int target) {
        if (count == 0) return;

        // two pointer
        int left = pos - 1, right = pos + 1;
        // make sure both pointer valid
        while(left >= 0 && right < arr.length) {
            if (count-- == 0) break;
            if (Math.abs(arr[left] - target) <= Math.abs(arr[right] - target)) {
                result.add(0, arr[left--]);
            } else {
                result.add(arr[right++]);
            }
        }

        // check when we have not reached k/count target
        // 左边走到头了， 全加右边, append
        // 右边左到头了，全加左边, add(0, num)
        while(count-- > 0) {
            if (left < 0) {
                result.add(arr[right++]);
            } else {
                result.add(0, arr[left--]);
            }
        }
    }
   @Test
   public void testCase(){
       int[] arr = {1, 1, 1, 10, 10, 10};
       List<Integer> result = new LinkedList<>();
       result = findClosestElements(arr, 1, 9);
       System.out.println(result);

   }

   @Test
   public void testOutputOrder() {
       int[] arr2 = {-2, -1, 1, 2, 3, 4, 5};
       List<Integer> result2 = new LinkedList<>();
       result2 = findClosestElements(arr2, 7, 3);
       System.out.println(result2);
   }

    @Test
    public void testOrder() {
        int[] arr3 = {0, 1, 2, 2, 2, 3, 6, 8, 8, 9};
        System.out.println(Arrays.toString(arr3));
        List<Integer> result3 = new LinkedList<>();
        result3 = findClosestElements(arr3, 5, 9);
        System.out.println(result3);
   }
}
