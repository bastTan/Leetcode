package BFS;

import java.util.*;

/**
 * start word to end word. given a dictionary.
 * return #words in the shorted transformation sequence
 *
 * S1. one direction
 * S2. two direction,两头走。等相遇。 begin set/ end set，每次找size最少的set 走. 每层还要一个set 放next set 和当前层替换
 * NOTE: convert, a ~ z, i = 'a', i <= 'z', i++
 */
public class _LC127_word_ladder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        dict.addAll(wordList);

        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        dict.remove(beginWord); // visited

        int minLen = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String curr = queue.poll();
                char[] currStr = curr.toCharArray();
                for (int i = 0; i < currStr.length; i++) {
                    char tmp = currStr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        currStr[i] = c;
                        String next = String.valueOf(currStr);
                        if (c != tmp && dict.contains(next)) {
                            if (next.equals(endWord)) {
                                return minLen + 1;
                            }
                            queue.offer(next);
                            dict.remove(next);
                        }
                    }
                    currStr[i] = tmp;
                }
            }
            minLen++;
        }
        return 0;
    }

    // Solution 2: two direction
    public int ladderLength2Dir(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        dict.addAll(wordList);

        if (!dict.contains(endWord)) return 0;
        if (beginWord.equals(endWord)) return 1;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        int minDist = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // pick the smaller one
            if (beginSet.size() > endSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            Set<String> nextLevel = new HashSet<>();
            for(String cur : beginSet) {
                char[] cc = cur.toCharArray();
                for (int i = 0; i < cc.length; i++) {
                    char tmp = cc[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        cc[i] = c;
                        String str = String.valueOf(cc);
                        if (endSet.contains(str)) return minDist + 1;
                        if (c != tmp && dict.contains(str)) {
                            nextLevel.add(str);
                            dict.remove(str);
                        }
                    }
                    cc[i] = tmp;
                }
            }
            beginSet = nextLevel;
            minDist++;
        }
        return 0;
    }
}
