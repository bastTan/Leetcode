package LinkedList;

// partition base on value not position
// use 5 helper Node
// curr -> traverse current list
// dummy1 (< target head), c1 traverse dummy1 list
// dummy2 (> target head), c2 traverse dummy2 list
public class LC_List_partition {
    public ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-2);
        ListNode c1 = dummy1;
        ListNode c2 = dummy2;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val < x) {
                c1.next = curr;
                c1 = c1.next;
            } else {
                c2.next = curr;
                c2 = c2.next;
            }
            curr = curr.next;
        }

        c2.next = null;
        c1.next = dummy2.next;
        return dummy1.next;
    }
}
