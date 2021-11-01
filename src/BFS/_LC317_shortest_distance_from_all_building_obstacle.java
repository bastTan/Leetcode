package BFS;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * input: 2D matrix. 0 = empty space, 1 = building, 2 = obstacle
 * output: shortest travel distance from a '0' to all buildings
 * if no 0 can reach all builds, return -1;
 * Note:
 *  - cost all initialized 0, but a valid cost can not be 0, need to check before return
 * [!Edge case!]
 *  - 有零可能不能到达所有的1.那这个0对应的cost不valid
 *      - 如果从某个1出发。发现有没摸到的0，这个0应该被mark off
 *
 *
 * solution: run three time BFS, need a visited to mark used '0', reuse the same result board
 */
public class _LC317_shortest_distance_from_all_building_obstacle {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return -1;
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] cost = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j]  == 1) {
                    bfs(grid, i, j, cost);
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    res = Math.min(res, cost[i][j]);
                }
            }
        }
        return res;
    }

    private void bfs(int[][] mat, int i, int j, int[][] cost) {
        Queue<Integer> queue = new LinkedList<>();
        int minDist = 1;
        int rows = mat.length;
        int cols = mat[0].length;
        boolean[][] visited = new boolean[rows][cols];
        queue.offer(i * cols + j);
        int[][] Directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                int x = curr / cols;
                int y = curr % cols;
                for (int[] dir: Directions) {
                    int xx = x + dir[0];
                    int yy = y + dir[1];
                    if (xx >= 0 && xx < rows && yy >= 0 && yy < cols &&
                            mat[xx][yy] == 0 && !visited[xx][yy]
                    ) {
                        visited[xx][yy] = true;
                        queue.offer(xx * cols + yy);
                        cost[xx][yy] += minDist;
                    }
                }
            }
            minDist++;
        }
        // remove invalid '0' (non-reachable 0 for certain building, can't count as valid spot)
        for (int ii= 0; ii < rows; ii++) {
            for (int jj = 0; jj < cols; jj++)  {
                if (mat[ii][jj] == 0 && !visited[ii][jj]) {
                    mat[ii][jj] = 2;
                }
            }
        }
    }
    @Test
    public void test(){

        int[][] mat = {
                {1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };
        int ret = shortestDistance(mat);
        System.out.println(Arrays.deepToString(mat));
        System.out.println(ret);
    }
}
