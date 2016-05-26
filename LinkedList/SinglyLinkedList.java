public class SinglyLinkedList<E> {

    public Node<E> first;
    int size;

    SinglyLinkedList() {
        first = null;
        size = 0;
    }

    public class Node<E> {
        Node<E> next;
        E data;
    }

    void insertAtLast(E data) {
        if (size == 0) {
            first = new Node<>();
            first.data = data;
            first.next = null;
        } else {
            Node<E> currentNode = getNodeAtPosition(size - 1);
            Node<E> newNode = new Node<>();
            newNode.data = data;
            newNode.next = null;
            currentNode.next = newNode;

        }
        size++;
    }

    void insertAtFirst(E data) {
        if (size == 0) {
            first = new Node<>();
            first.data = data;
            first.next = null;
        } else {
            Node<E> newNode = new Node<>();
            newNode.data = data;
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    Node getNodeAtPosition(int position) {
        if (position >= size || position < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node temp = first;
        int counter = 0;
        for (; counter < position; counter++) {
            temp = temp.next;
        }
        return temp;
    }

    public void insertNodeAtPosition(int position, E data) {
        if (position == 0) {
            insertAtFirst(data);
        } else if (position == size - 1) {
            insertAtLast(data);
        } else {
            Node<E> tempNode = getNodeAtPosition(position - 1);
            Node<E> newNode = new Node<>();
            newNode.data = data;
            newNode.next = tempNode.next;
            tempNode.next = newNode;

        }
        size++;
    }

    public E removeAtPosition(int position) {
        Node currentNode = getNodeAtPosition(position);
        E data = (E) currentNode.data;
        Node tempNode = getNodeAtPosition(position - 1);
        tempNode.next = tempNode.next.next;
        size--;
        return data;
    }

    public E removeAtLast() {
        Node currentNode = getNodeAtPosition(size - 1);
        E data = (E) currentNode.data;
        Node tempNode = getNodeAtPosition(size - 2);
        tempNode.next = null;
        size--;
        return data;
    }

    @Override
    public String toString() {
        String result = "";
        Node current = first;
        if (current != null) {
            while (current.next != null) {
                result += current.data + ", ";
                current = current.next;
                if (current.next == null) {
                    result += current.data + ", ";
                }
            }
        }

        return "List: " + result;
    }
