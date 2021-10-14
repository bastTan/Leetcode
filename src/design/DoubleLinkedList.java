package design;

public class DoubleLinkedList {

    private class ListNode{
        int val;
        ListNode prev;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    private int curSize;
    private ListNode head, tail; // dummy

    public DoubleLinkedList() {
        this.head = new ListNode(0);
        this.tail = new ListNode(0);

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int size() {
        return this.curSize;
    }

    public int get(int index) {
        checkIndex(index);
        return getIthNode(index).val;
    }
    // add before the tail
    public void add(int val) {
        ListNode newNode = new ListNode(val);
        ListNode prev = this.tail.prev;

        prev.next = newNode;
        newNode.prev = prev;

        tail.prev = newNode;
        newNode.next = tail;

        this.curSize++;
    }
    public void add(int index, int val){
        checkIndex(index);
        ListNode newNode = new ListNode(val);

        ListNode prev = getIthNode(index).prev;
        ListNode next = prev.next;

        // newNode <> next
        newNode.next = next;
        next.prev = newNode;

        // prev <> newNode
        newNode.prev = prev;
        prev.next = newNode;

        this.curSize++;
    }

    public void remove(int index) {
        checkIndex(index);

        ListNode node = this.getIthNode(index);

        ListNode prev = node.prev;
        ListNode next = node.next;

        prev.next = next;
        next.prev = prev;

        this.curSize--;
    }
    private void checkIndex(int index) {
        if (index < 0 || index >= this.curSize) {
            throw new RuntimeException("Out of boundary");
        }
    }

    private ListNode getIthNode(int i) {
        ListNode curr = this.head.next;
        while (i-- > 0) {
            curr = curr.next;
        }
        return curr;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();

        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val + ", ");
            curr = curr.next;
        }
       System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        list.print();

        list.add(4, 80);
        list.print();
        list.add(0, 80);
        list.print();

        list.remove(0);
        list.print();

        list.remove(6);
        list.print();
    }
}
