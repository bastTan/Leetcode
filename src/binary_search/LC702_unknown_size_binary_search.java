package binary_search;

// given API get(index). don't know upper range
// step1: 反向binary search, while（reader.get(right) < target), right *= 2；
// step2：正向BS
public class LC702_unknown_size_binary_search {
    private class ArrayReader {
        int[] array;
        ArrayReader() {
            array = new int[100];
        }
        int get(int index) {
            return array[index];
        }
    }
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) {
            return 0;
        }
        // 反向binery search， search bounderies.
        int left = 0, right = 1;
        while (reader.get(right) < target) {
            left = right;
            right <<= 1;
        }

        // 正向 binarysearch，s1，search target
        return binarySearch(reader, left, right, target);
    }

    private int binarySearch(ArrayReader reader, int left, int right, int target) {
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (reader.get(mid) == target) return mid;
            else if (reader.get(mid) > target) right = mid - 1;
            else left = mid + 1;
        }

        return -1;
    }
}
