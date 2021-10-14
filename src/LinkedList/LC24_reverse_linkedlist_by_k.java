package LinkedList;

// 1 -> 2 -> 3 -> 4
// 2 -> 1 -> 4 -> 3
public class LC24_reverse_linkedlist_by_k {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode nextHead = swapPairs(head.next);
        ListNode newHead = head.next;
        newHead.next = head;
        head.next = nextHead;
        return newHead;
    }
}
