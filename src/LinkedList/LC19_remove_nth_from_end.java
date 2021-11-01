package LinkedList;

/**
 * s1, find length, then move. two pass
 * s2. two pointers start from dummy, first move n+1 steps. maintain 'N' gap
 *     then walk together.
 */
public class LC19_remove_nth_from_end {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy, second = dummy;

        // find N+1th node from the end
        // maintain 'N' gap. between slow and fast
        for (int i = 0; i < n+1; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // Second --> target --> next
        // second --------------> next

        second.next = second.next.next;
        return dummy.next;
    }
}
