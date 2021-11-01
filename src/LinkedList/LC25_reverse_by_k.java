package LinkedList;

// less than k part, remain the same
// reverse every k group
// i.e. reverseKGroup(head, 3)
// 1 -> 3 -> 4 -> 6 -> 8
// 4 -> 3 -> 1 -> 6 -> 8
public class LC25_reverse_by_k {
    // step1: find k's node
    // step2: reverse node 1 -> k -> null
    // step3: reverseKGroup(k.next, k);
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int count = 0;
        ListNode curr = head;
        // walk k-1 times
        while (count++ < k - 1) {
            curr = curr.next;
            // less than k node, return
            if (curr == null) return head;
        }
        ListNode nextHead = reverseKGroup(curr.next, k);
        curr.next = null;
        ListNode newHead = reverse(head);
        head.next = nextHead;
        return newHead;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode curr = head;
        ListNode prev = null, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
