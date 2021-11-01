package LinkedList;

/**
 * max Int is 2^31
 * adding two number may overflow
 * best representation is using LinkedList
 * but reverse it so that we don't need to worry about 对齐问题
 * use list to reversely represent a number
 */
public class LC2_add_two_number_as_list {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p1 = l1, p2 = l2, curr = dummy;
        int carry = 0;
        while (p1 != null || p2 != null) {
            int x = (p1 == null) ? 0 : p1.val;
            int y = (p2 == null) ? 0 : p2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
