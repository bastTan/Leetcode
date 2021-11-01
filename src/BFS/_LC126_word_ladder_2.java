package BFS;

import java.util.*;

/**
 * return all shortest path from begin word to end word
 * compare to word_ladder_1
 * 1. build graph while BFS, can guarantee only shortest path being built
 * 2. for more efficient recover path, 反建边。next -> CURR,  这样从end 开始走还原path 的时候可以guarantee 走的通
 * 3. 不能找到end word就返回。要确保这一层找完。才不会丢解
 * 4。两种visited。一种全局visited。一种被当前层visited过。被当前层visited过就不用offer进queue。need a visitedThisLevel Set
 *
 *
 */
//
public class _LC126_word_ladder_2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();

        Set<String> dict = new HashSet<String>();
        dict.addAll(wordList);
        Queue<String> queue = new LinkedList<>();
        Map<String, List<String>> graph = new HashMap<>();
        boolean flag = false;
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // string already touched by other elements in the same level
            Set<String> visitedThisLev = new HashSet<>();
            while (size-- > 0) {
                String curr = queue.poll();
                char[] cc = curr.toCharArray();
                for (int i = 0; i < cc.length; i++) {
                    char tmp = cc[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        cc[i] = c;
                        String next = String.valueOf(cc);
                        if (c == tmp || !dict.contains(next)) continue;
                        if (next.equals(endWord)) flag = true; // found endWord, but need to still finish current level to build graph edges
                        // 第一次见，没被本层其它word访问过
                        // build edge: next -> curr
                        if (visitedThisLev.add(next)) {
                            queue.offer(next);
                            List<String> list = new ArrayList<String>();
                            list.add(curr);
                            graph.put(next, list);
                        } else { // seen by same level word，dont offer twice，only build edge
                            List<String> list = graph.get(next);
                            list.add(curr);
                            graph.put(next, list);
                        }
                    }
                    cc[i] = tmp;
                }
            } // end of this level
            dict.removeAll(visitedThisLev);

            // found end word in this level, recover path from graph
            if (flag) {
                List<String> path = new LinkedList<>();
                path.add(endWord);
                // build path from end to start, in reverse order
                dfs(res, path, endWord, beginWord, graph);
                return res;
            }
        }
        return res;
    }

    public void dfs(List<List<String>> res, List<String> path, String curr, String end,
                    Map<String, List<String>> graph) {
        if (curr.equals(end)) {
            List<String> copy = new LinkedList<String>(path);
            res.add(copy);
            return;
        }
        List<String> next = graph.get(curr);
        for (String str : next) {
            path.add(0, str);
            dfs(res, path, str, end, graph);
            path.remove(0);
        }
    }
}

