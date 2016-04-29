public class LinkedListLoop {

    static void isLoopDetected(SinglyLinkedList.Node first) {
        SinglyLinkedList.Node slow, fast, start;
        slow = fast = first;
        while (true) {
            if (fast == null || fast.next == null) {
                return;
            } else if (fast.data == slow.data || fast.next.data == slow.data) {
                break;
            } else {
                fast = fast.next.next;
                slow = slow.next;
            }

        }

        fast = first;
        while (fast.next.data != slow.next.data) {
            fast = fast.next;
            slow = slow.next;
        }

        start = fast.next;

        fast = start;
        while (fast.next.data != start.data) {
            fast = fast.next;
        }

        fast.next = null;

    }
}
