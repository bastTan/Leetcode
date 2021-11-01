package BFS;

import Utils.TreeGenerator;
import Utils.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 */
public class LC103_binaryTree_zigzag_level_order_traversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isReverse = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currLevel = new LinkedList<>();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                currLevel.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            if (isReverse) {
                Collections.reverse(currLevel);
            }
            result.add(currLevel);
            isReverse = !isReverse;
        }
        return result;
    }
    @Test
    public void test() {
        TreeGenerator generator = new TreeGenerator();
        String tree = "3,9,20,#,#,15,7,16,3,2,7";
        TreeNode root = generator.deserialize(tree);
        System.out.println(root);
        List<List<Integer>> result = zigzagLevelOrder(root);
        System.out.println(result);
    }
}
