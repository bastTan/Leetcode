package BFS;

import Utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * tranverse binary tree
 * print each level
 * bottom up order
 * solution: BFS, but always add to the head of the result list
 */
public class LC107_binary_tree_level_order_tranverse_2 {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {

            List<List<Integer>> res = new LinkedList<>();
            if (root == null) return res;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> levelList = new ArrayList<>();
                while (size-- > 0) {
                    TreeNode curr = queue.poll();
                    levelList.add(curr.val);
                    if (curr.left != null) queue.offer(curr.left);
                    if (curr.right != null) queue.offer(curr.right);
                }
                res.add(0, levelList);
            }
            return res;
        }
    }
