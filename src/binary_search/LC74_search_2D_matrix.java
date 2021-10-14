package binary_search;

// 1  3  5  7
// 10 11 16 20
// 23 30 34 60
// sorted array in 2D from left to right
// treat as a normal array
public class LC74_search_2D_matrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0 ) return false;

        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int row = mid / cols;
            int col = mid % cols;
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) left = mid + 1;
            else                                right = mid - 1;
        }
        return false;
    }
}
