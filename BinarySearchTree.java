package DataStructures;

/**
 * Created by Syril on 18-03-2016.
 */
public class BinarySearchTree {

    public Node root = null;

    public Node getRoot() {
        return root;
    }

    public class Node {
        public Node left;
        public Node right;
        int level;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public void insert(int data) {
        root = put(root, data);
    }

    public Node put(Node n, int value) {
        if (n == null) {
            return new Node(value);
        } else {
            if (value < n.data)
                n.left = put(n.left, value);
            if (value > n.data)
                n.right = put(n.right, value);
        }
        //n.count =1+size(n.left)+size(n.right);

        return n;
    }
}
