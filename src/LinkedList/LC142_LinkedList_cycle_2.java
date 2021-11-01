package LinkedList;

/**
 * floyd's alg
 * 先找slow fast 相遇的点
 * 然后 用 p = head, 和slow 齐步走
 */
public class LC142_LinkedList_cycle_2 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (slow == fast) {
                ListNode p = head;
                while (head != slow) {
                    slow = slow.next;
                    p = p.next;
                }
                return slow;
            }
        }
        return null;
    }
}
