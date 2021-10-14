package binary_search;

/*
 *    1  4  7  11  15
 *    2  5  8  12  19
 *    3  6  9  16  22
 *    10 13 14 17  24
 *    18 21 23 26  30
 *    right > left
 *    down > up
 *    1 -> 不能把size 减半，下面也比他大，右边也比他大
 */


public class LC240_search_2D_matrix_2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int r = row - 1;
        int c = 0;  // left bottom corner

        while (r >= 0 && c < col) {
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] < target) {
                c++; // find larger
            } else  { // find smaller
                r--;
            }
        }
        return false;
    }
}
