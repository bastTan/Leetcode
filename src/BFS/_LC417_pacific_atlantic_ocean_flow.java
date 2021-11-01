package BFS;

import org.junit.Test;

import java.util.*;

public class _LC417_pacific_atlantic_ocean_flow {
    private static int[][] Directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        // put pacific points into queue;
        for (int i = 0; i < rows; i++) {
            queue.offer(i * cols + 0);
            pacific[i][0] = true;
        }
        for (int j = 1; j < cols; j++) {
            queue.offer(0 * cols + j);
            pacific[0][j] = true;
        }
        // bfs from pacific
        bfs(heights, queue,res,pacific, atlantic);
        // put atlantic points into queue;
        for (int i = 0; i < rows; i++) {
            queue.offer(i * cols + cols - 1);
            atlantic[i][cols - 1] = true;
        }

        for (int j = 0; j < cols-1; j++) {
            queue.offer((rows - 1) * cols + j);
            atlantic[rows - 1][j] = true;
        }
        // bfs from atlantic;
        bfs(heights, queue, res, atlantic, pacific);
        return res;
    }

    public void bfs(int[][] heights, Queue<Integer> queue, List<List<Integer>>res, boolean[][] thisBoard, boolean[][] thatBoard) {
        int rows = heights.length;
        int cols = heights[0].length;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int i = curr / cols;
            int j = curr % cols;
            if (thatBoard[i][j]) {
                // both direction meeting here
                res.add(Arrays.asList(i, j));
            }
            for (int[] dir : Directions) {
                int ii = i + dir[0];
                int jj = j + dir[1];
                if (ii >= 0 && ii < rows && jj >= 0 && jj < cols &&
                        !thisBoard[ii][jj] && heights[ii][jj] >= heights[i][j]) {
                    queue.offer(ii * cols + jj);
                    thisBoard[ii][jj] = true;
                }
            }
        }
    }

    @Test
    public void test() {
        int[][] mat = {
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };
        List<List<Integer>> res = pacificAtlantic(mat);
        System.out.print(res);
    }

}
