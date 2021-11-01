package LinkedList;

import java.util.HashSet;
import java.util.Set;

/**
 *  S1. HashSet, 查重
 *  S2. slow, fast.
 */
public class LC141_LinkedList_cycle {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            if (seen.contains(head)) {
                return true;
            }
            seen.add(head);
            head = head.next;
        }
        return false;
    }

    public boolean hasCycleS2(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
