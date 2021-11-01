package BFS;

import Utils.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [[1,1],[2],[1,1] 本质上是多x树。只有leaf上有value。
 * level order traversal，-> BFS
 *
 * 变形LC364，倒序weighted sum。
 * 原来是level sum * depth
 * 现在是 depth reverse, depth = （maxdepth - depth of the integer） + 1
 * sln: 每往下走一层。之前的所有value 就被垫高了一次，所以就要再加一次。
 * levelSum -> valueSum
 * 每层走完 sum += levelsum * level -> sum += valueSum,
 * 不需要depth
 */
public class _LC339_nested_list_weight_sum {
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger ni : nestedList) {
            queue.offer(ni);
        }

        int sum = 0, level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            while (size-- > 0) {
                NestedInteger curr = queue.poll();
                if (curr.isInteger()) {
                    levelSum += curr.getInteger();
                    continue;
                }
                for (NestedInteger ni: curr.getList()) {
                    queue.offer(ni);
                }
            }
            sum += levelSum * level;
            level++;
        }
        return sum;
    }
}
