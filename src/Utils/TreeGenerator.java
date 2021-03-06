package Utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: techbow  LC 449
 * serialize: Tree -> string
 * deserialize: string -> tree
 * string: # -> null node, no space allowed
 * i.e. "2,3,4,#,7"
 *      2
 *     / \
 *    3   4
 *   / \
 * null 7
 */
public class TreeGenerator {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            if (cur == null) {
                sb.append("#,");
            } else {
                sb.append(cur.val + ",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }

        // try to compress as possible as it can
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] ss = data.split(",");
        System.out.println(Arrays.toString(ss));

        if (ss.length == 0) {
            throw new IllegalArgumentException();
        }
        if (ss.length == 1 && ss[0].equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(ss[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < ss.length) {
            TreeNode cur = queue.poll();

            TreeNode left = ss[i].equals("#") ?
                            null : new TreeNode(Integer.valueOf(ss[i]));
            TreeNode right = (++i >= ss.length || ss[i].equals("#")) ?
                            null : new TreeNode(Integer.valueOf(ss[i]));

            cur.left = left;
            cur.right = right;

            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
            i++;
        }
        return root;
    }
}
