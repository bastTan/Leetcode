package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2D board
 * 在边上一圈的O不算
 * 只要能被边上一圈的O 碰到就safe
 * 其余的O全变X
 *
 * idea:
 *  step1: put all boarder 'O' -> Y, put in queue
 *  step2: BFS, touch all safe O, change to Y, put in queue
 *  step3: flip Y to O, flip none'Y
 *
 */
public class LC130_surrounded_region {
    public class Point {
        int x;
        int y;
        public Point(int i, int j) {
            this.x = i;
            this.y = j;
        }
    }
    public void solve(char[][] board) {
        // flip escaped cells to its final state

        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        int row = board.length, col = board[0].length;
        Queue<Point> queue = new LinkedList<>();

        // step1. consturct all the border cells that are 'O'
        for (int i = 0; i < row; i++){
            // first col
            if (board[i][0] == 'O') {
                queue.offer(new Point(i, 0));
            }
            // rightmost col
            if (board[i][col-1] == 'O') {
                queue.offer(new Point(i, col-1));
            }
        }

        for (int j = 0; j < col; j++) {
            if (board[0][j] == 'O') {
                queue.offer(new Point(0, j));
            }
            if (board[row - 1][j] == 'O') {
                queue.offer(new Point(row-1, j));
            }
        }

        // step 2: mark all escapedbal cells (touchable by boarder cells)
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            int i = curr.x, j = curr.y;
            if (i-1 >= 0 && board[i-1][j] == 'O') {
                board[i-1][j] = 'Y';
                queue.offer(new Point(i-1, j));

            }
            if (i+1 < row && board[i+1][j] == 'O') {
                board[i+1][j] = 'Y';
                queue.offer(new Point(i+1, j));

            }
            if (j+1 < col && board[i][j+1] == 'O') {
                board[i][j+1] = 'Y';
                queue.offer(new Point(i, j+1));

            }
            if (j-1 >= 0 && board[i][j-1] == 'O') {
                board[i][j-1] = 'Y';
                queue.offer(new Point(i, j-1));
            }
        }

        // step 3: Y -> O, O -> X
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == 'Y') board[i][j] = 'O';
            }
        }
    }
}
