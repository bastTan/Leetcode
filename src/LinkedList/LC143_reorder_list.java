package LinkedList;

import org.junit.Test;

public class LC143_reorder_list {
        public ListNode reorderList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) return null;

            ListNode mid = findMid(head);
            ListNode leftHead = head;
            ListNode rightHead = mid.next;
            mid.next = null;
            return merge(leftHead, reverse(rightHead));
        }

        // if even, mid is the left one
        private ListNode findMid(ListNode head) {
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        private ListNode reverse(ListNode head) {
            if (head == null || head.next == null) return head;

            ListNode newHead = reverse(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }
        // l1.length == l2.length  or
        // l1.length + 1 == l2.length
        public ListNode merge(ListNode l1, ListNode l2) {
            ListNode p1 = l1, p2 = l2;
            ListNode next1, next2;
            while (p2 != null) {
                next1 = p1.next;
                next2 = p2.next;
                p1.next = p2;
                p2.next = next1;
                p1 = next1;
                p2 = next2;
            }
            return l1;
        }

    @Test
    public void testMerge() {
        ListUtil util = new ListUtil();
        int[] array = {1, 2 };
        int[] array2 = {3, 4};
        ListNode head1 = util.genList(array);
        ListNode head2 = util.genList(array2);
        ListNode mergedHead = merge(head1, head2);
        util.print(mergedHead);
    }

    @Test
    public void testMid() {
        ListUtil util = new ListUtil();
        int[] array = {1, 2, 3, 4, 5};
        ListNode head1 = util.genList(array);
        ListNode mid = findMid(head1);
        System.out.print(mid.val);
    }

    @Test
    public void testReverse() {
        ListUtil util = new ListUtil();
        int[] array = {1, 2, 3, 4, 5};
        ListNode head1 = util.genList(array);
        ListNode reversedHead = reverse(head1);
        util.print(reversedHead);
    }

    @Test
    public void testReorder() {
        ListUtil util = new ListUtil();
        int[] array = {1, 2, 3, 4};
        ListNode head1 = util.genList(array);
        ListNode newHead = reorderList(head1);
        util.print(newHead);
    }

}