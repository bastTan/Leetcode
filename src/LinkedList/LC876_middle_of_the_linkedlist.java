package LinkedList;

/** discuss when length == even, return left, or right node
 *  1 2  3 4  5 6
 *       l r
 * slow fast pointer
 * if return left, then slow = head, fast = head.next
 * else                 slow = head, fast = head
 *
 */
public class LC876_middle_of_the_linkedlist {
    // if even, return the right node
    public ListNode middleNode(ListNode head) {
        // corner
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
