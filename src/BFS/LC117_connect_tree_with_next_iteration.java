package BFS;

/**
 * LC116, BFS with prev pointer still valid
 * new solution: iteration
 * need next level head, and next level prev.
 * current level already set, connect next level while traverse current level
 */
public class LC117_connect_tree_with_next_iteration {
    class Node {
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
    };
    public Node connect(Node root) {
        if (root == null) return root;
        // next level prev
        Node prev = null;
        // next level head
        Node head = null;

        Node curr = root;
        while (curr != null) {
            while (curr != null) {
                if (curr.left != null) {
                    if (prev != null) {
                        prev.next = curr.left;
                    } else {
                        head = curr.left;
                    }
                    prev = curr.left;
                }
                if (curr.right != null) {
                    if (prev != null) {
                        prev.next = curr.right;
                    } else {
                        head = curr.right;
                    }
                    prev = curr.right;
                }
                curr = curr.next;
            } // end of current level
            curr = head;
            head = null;
            prev = null;
        }

        return root;
    }
}
