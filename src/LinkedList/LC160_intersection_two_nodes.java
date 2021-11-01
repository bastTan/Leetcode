package LinkedList;

import org.junit.Test;

/**
 * intuiative:
 * 把两个list 分别放到两个stack里， 然后同时往外pop。 第一个不相同的node 的下一个就是intersect。 但是要O(m,n) space
 * time = O(m+n), space = O(1)
 *  S1, 先找到l1，l2的length。长的那个走完多余的部分， 再一起走
 *  S2. 两个pointer分别从两个list 开始走，一个走完了就跳到另一个list 继续走。这样大家总长一样。不用length
 *      1 2 3 4 5 6 7
 *           /
 *       2 3
 */
public class LC160_intersection_two_nodes {
    // S1, with length
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = length(headA);
        int lenB = length(headB);

        ListNode p1 = headA, p2 = headB;
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++)  p1 = p1.next;
        } else {
            for (int i = 0; i < lenB - lenA; i++) p2 = p2.next;
        }

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    // S2. without length.
    public ListNode getIntersectionNodeNoLength(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null? headB: p1.next;
            p2 = p2 == null? headA: p2.next;
        }
        return p1;
    }

    private int length(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }

    @Test
    public void test() {
        System.out.println("ha");
    }
}
