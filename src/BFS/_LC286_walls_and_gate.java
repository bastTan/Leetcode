package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// question:
// -1 a wall is an obstacle
// 0 a gate
// INF an empty room
// 要求更新每个empty room 到gate 的最短距离
//  ------------------------------------------------------------
// 从gate出发，找最短路径, queue  按层 更新， 层 == dist。第一层，dist 为一，第二层，dist 为2， etc
// 第一次摸到这个不是wall的点，一定是更新的是最短距离
// BFS->第一层放所有门 -> pull -> find all its neighbor(none wall) -> if neighbor is empty room, update distance
public class _LC286_walls_and_gate {
    public class Point {
        int i;
        int j;
        Point(int x, int y) {
            i = x;
            j = y;
        }
    }

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) return;

        // 把门先扔进queue, 每个门能摸到的点都是距离为一的点
        Queue<Point> queue = new LinkedList<Point>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                // put all gates
                if (rooms[i][j] == 0) {
                    queue.offer(new Point(i, j));
                }
            }
        }

        int dist = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                Point curr = queue.poll();
                List<Point> list = convert(curr, rooms); // find all its neighbor
                for (Point p : list) {
                    int i = p.i, j = p.j;
                    if (rooms[i][j] == Integer.MAX_VALUE) {
                        rooms[i][j] = dist;
                        queue.offer(p);
                    }
                }
            }
            dist++;
        }
    }

    // def: neighbor, 向上，向下，向左，向右， not wall(-1)
    private List<Point> convert(Point p, int[][] grid) {
        List<Point> list = new ArrayList<>();
        int i = p.i, j = p.j;
        int rows = grid.length;
        int cols = grid[0].length;
        // up
        if (i-1 >= 0 && grid[i-1][j] != -1) {
            list.add(new Point(i-1, j));
        }
        // down
        if (i+1 < rows  && grid[i+1][j] != -1) {
            list.add(new Point(i+1, j));
        }
        // left
        if (j-1 >= 0 && grid[i][j-1] != -1) {
            list.add(new Point(i, j-1));
        }
        // right
        if (j+1 < cols && grid[i][j+1] != -1) {
            list.add(new Point(i, j+1));
        }
        return list;
    }
}
