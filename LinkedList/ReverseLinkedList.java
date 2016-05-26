package DataStructures;

/**
 * Created by Syril on 18-03-2016.
 */
public class ReverseLinkedList {

    public static SinglyLinkedList reverseLinkedList(SinglyLinkedList singlyLinkedList) {
        if (singlyLinkedList.size == 0)
            return new SinglyLinkedList();
        SinglyLinkedList.Node current, prev, next;
        prev = null;
        current = singlyLinkedList.first;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        singlyLinkedList.first = prev;
        return singlyLinkedList;
    }
}
