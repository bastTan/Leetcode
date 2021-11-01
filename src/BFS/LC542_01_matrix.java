package BFS;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * update all non-zero cells shortest distance to 0
 * 0 0 0       0 0 0
 * 0 1 0    -> 0 1 0
 * 1 1 1       1 2 1
 * step1: 不能覆写原matrix， 需要原来的info。new result 2D matrix，new queue， 原来的0全丢进queue
 * step2: 上下左右能走的，mat 是1的，result 是0的 （not visited yet）更新result里最短距离。丢进queue
 */
public class LC542_01_matrix {
    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length  == 0) {
            return mat;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int rows = mat.length;
        int cols = mat[0].length;
        int[][] result = new int[rows][cols];


        // put all zeros;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(i * cols + j);
                }
            }
        }

        int minDist = 1;
        while (!queue.isEmpty()) {
            int size =  queue.size();
            while (size -- > 0) {
                int curr = queue.poll();
                for (int[] dir : directions) {
                    int i = curr / cols + dir[0];
                    int j = curr % cols + dir[1];
                    if ( i >= 0 && i < rows && j >= 0 && j < cols && mat[i][j] == 1 && result[i][j] == 0) {
                        result[i][j] = minDist;
                        queue.offer(i * cols + j);
                    }
                }
            }
            minDist++;
        }
        return result;
    }

    @Test
    public void test() {
        int[][] mat = {{0,0,0},{0,1,0},{1,1,1}};
        System.out.println(Arrays.deepToString(mat));
        int[][] ret = updateMatrix(mat);
        System.out.println(Arrays.deepToString(ret));
    }
}
