package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * given a perfect binary tree.
 * all leaves on the same level, every parent has two children
 *    1
 *   2  3
 *  4 5 6 7
 *
 *      1 -> null
 *    2 -> 3 -> null
 *  4->5->6->7-> null
 */

public class LC116_connect_tree_with_next {
    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    public Node connect(Node root) {
        if (root == null) return root;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;
            while (size-- > 0) {
                Node curr = queue.poll();
                if (prev != null) prev.next = curr;
                curr.next = null;
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
                prev = curr;
            }
        }

        return root;
    }
}
