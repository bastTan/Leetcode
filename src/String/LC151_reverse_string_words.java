package String;

import java.util.*;

/**
 *
 * string may contain leading spaces, number, multiple spaces in between, trailing spaces.
 * reverse each word
 * Input: s = "the sky is    blue"
 * Output: "blue is sky the"
 * s1. use build in
 *     - remove leading/trailing spaces     s.trim()
 *     - split by multiple spaces,          s.split()
 *     - convert to List<String>,           Arrays.asList();
 *     - reverse each word in the list,     Collections.reverse()
 * s2. reverse everything, then reverse each word
 *      - trim leading/trailing, multiple in between spaces
 *      - reverse the whole thing, swap left/right
 *      - find each word, then reverse
 * s3. use stack.
 *      - trim
 *      - use StringBuilder, offer each word to stack, deque.offerFirst
 *      - string.join()
 */
public class LC151_reverse_string_words {
    public String reverseWordsBuildIn(String s) {
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public String reverseWords(String s) {
        // trim leading/trailing spaces
        // compress multiple spaces to single spaces
        // convert string to array
        StringBuilder sb = trimSpaces(s);
        // reverse whole array
        reverse(sb, 0, sb.length() - 1);
        // reverse each word
        reverseEachWord(sb);
        // join
        return sb.toString();
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            // swap left/right char
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);

        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int len = sb.length();
        int start = 0, end = 0;
        while (start < len) {
            // find the end of a word
            while (end < len && sb.charAt(end) != ' ') ++end;
            reverse(sb, start, end-1);
            // go to the next word
            start = end + 1;
            end = start;
        }
    }

    // trim (front, end spaces)
    // convert multiple middle spaces to one
    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') ++left;
        while (left <= right && s.charAt(right) == ' ') --right;

        // reduce multiple spaces to single one
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') sb.append(c);
                // if sb doesn't end with space, attach the first space to it only
            else if (sb.charAt(sb.length() - 1) != ' ') sb.append(c);
            ++left;
        }
        return sb;
    }

    public String revserseWordsDeque(String s) {
        int left = 0, right = s.length() - 1;
        // remove leading 0
        while (left <= right && s.charAt(left) == ' ') left++;
        // remove trailing 0
        while (left <= right && s.charAt(right) == ' ') right--;

        Deque<String> d = new ArrayDeque();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);

            if (c == ' ' && word.length() != 0)  {
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());
        return String.join(" ", d);
    }
}
