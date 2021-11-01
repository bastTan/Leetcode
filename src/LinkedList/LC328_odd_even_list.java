package LinkedList;


/**
 * odd/even base on position
 * first is odd, second node is even.
 * link all odd node together, then append all even node together.
 *               odd   even
 *  head --->     1 -> 2 -> 3 -> 4 -> 5
 *                    /
 *           evenHead
 *
 *                          odd
 *                1  -----> 3 -> 4
 *                             /
 *                2 ----------
 *               even.next = odd.next
 *
 */
public class LC328_odd_even_list {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode odd = head, even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
