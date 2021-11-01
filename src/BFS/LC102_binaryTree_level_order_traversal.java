package BFS;

import Utils.TreeGenerator;
import Utils.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *     3
 *   /   \
 *  9     20
 *       /  \
 *      15   7
 * Input: root = [3,9,20,null,null,15,7]
 * Output:       [[3],[9,20],[15,7]]
 */
public class LC102_binaryTree_level_order_traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currLevel = new ArrayList<>();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                currLevel.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            result.add(currLevel);
        }
        return result;
    }

    @Test
    public void test() {
        TreeGenerator generator = new TreeGenerator();
        String tree = "3,9,20,#,#,15,7";
        TreeNode root = generator.deserialize(tree);
        System.out.println(root);
        List<List<Integer>> result = levelOrder(root);
        System.out.println(result);
    }

}
