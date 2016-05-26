package DataStructures;

/**
 * Created by Syril on 26-05-2016.
 */
public class LinkedListMergePoint {
    static Node head1, head2;

    static class Node {

        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    int getNode() {
        int d;
        int c1 = count(head1);
        int c2 = count(head2);

        if (c1 > c2) {
            d = c1 - c2;
            return getIntersection(d, head1, head2);
        } else {
            d = c2 - c1;
            return getIntersection(d, head2, head1);
        }
    }

    int count(Node n) {
        int count = 0;
        while (n.next != null) {
            n = n.next;
            count++;
        }
        return count;
    }

    int getIntersection(int d, Node n1, Node n2) {
        for (int i = 0; i < d; i++) {
            if (n1 == null)
                return -1;
            n1 = n1.next;
        }

        while (n1 != null && n2 != null) {
            if (n1.data == n2.data) {
                return n1.data;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return -1;
    }

    public static void main(String[] args) {
        LinkedListMergePoint list = new LinkedListMergePoint();

        // creating first linked list
        list.head1 = new Node(3);
        list.head1.next = new Node(6);
        list.head1.next.next = new Node(15);
        list.head1.next.next.next = new Node(15);
        list.head1.next.next.next.next = new Node(30);

        // creating second linked list
        list.head2 = new Node(10);
        list.head2.next = new Node(15);
        list.head2.next.next = new Node(30);

        System.out.println("The node of intersection is " + list.getNode());

    }

}
