package LinkedList;

import org.junit.Test;

// S1: tranverse original list
// insert each after dummy head
// S2: iterative
// S3: recursion, reverse curr, curr.next, new head = reverse(head.next); head KEEP THE SAME
public class LC206_ReverseLinkedList {

    public LinkedList.ListNode reverse(LinkedList.ListNode head) {
        if (head == null) return head;

        ListNode dummy = new ListNode(-1);
        LinkedList.ListNode curr = head;
        while (curr != null) {
            LinkedList.ListNode next = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;

            curr = next;
        }
        return dummy.next;
    }

    public ListNode genList(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int val : array) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    public void print(ListNode head) {
        StringBuilder sb = new StringBuilder();

        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val + ", ");
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    // S2
    public LinkedList.ListNode reverseListIter(LinkedList.ListNode head) {
        if (head == null || head.next == null) return head;
        // 1 - 2 - 3 - 4 - 5
        LinkedList.ListNode prev = null;
        LinkedList.ListNode curr = head;
        while (curr != null) {
            LinkedList.ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // S3 recursion, new head = recursion(head.next), reverse curr.
    public LinkedList.ListNode reverseListRec(LinkedList.ListNode head) {
        // 0 or one node
        if (head == null || head.next == null) return head;

        LinkedList.ListNode newHead = reverseListRec(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    @Test
    public void testReverse() {
        int[] array = {1, 2, 3, 4, 5, 6};
        LinkedList.ListNode head = genList(array);
        print(head);
        head = reverse(head);
        print(head);
    }
}
