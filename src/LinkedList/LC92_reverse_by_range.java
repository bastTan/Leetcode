package LinkedList;

/**
 *  1- 2-3-4 -5 , reverse index range[2, 4]
 *  p1 c1  p2 c2
 *  1- 4-3-2- 5
 *
 *  1. dummy
 *  2. get p1, c1
 *  3. break p1 -> c1
 *  4. get p2, c2
 *  5. break p2 -> c2
 *  6 reverse c1
 *  7 put it back
 */
public class LC92_reverse_by_range {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p1 = dummy, c1 = head;
        for (int i = 1; i < left; i++) {
            p1 = c1;
            c1 = c1.next;
        }

        p1.next = null;

        ListNode p2 = c1, c2 = c1.next;
        for (int i = 0; i < right - left; i++) {
            p2 = c2;
            c2 = c2.next;
        }

        p2.next = null;
        ListNode reversedHead = reverseDummy(c1);
        p1.next = reversedHead;
        c1.next = c2;

        return dummy.next;
    }

    private ListNode reverseDummy(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode curr = head;
        ListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = next;
        }
        return dummy.next;
    }
}
