package LinkedList;

public class ListUtil {
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
}
