package DataStructures;

/**
 * Created by Syril on 23-05-2016.
 */
public class LinkedListReverseSegment {

    public static SinglyLinkedList.Node reverseLinkedList(SinglyLinkedList.Node head, int k) {
        SinglyLinkedList.Node current = head;
        SinglyLinkedList.Node prev = null;
        SinglyLinkedList.Node next = null;
        int count = 0;
        while (count < k && current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        if (next != null) {
            head.next = reverseLinkedList(next, k);
        }
        return prev;
    }
}
