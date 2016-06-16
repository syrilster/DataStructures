    Node root;

    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    public Node findLCA(int n1, int n2) {
        return findLCA(root, n1, n2);
    }

    Node findLCA(Node node, int n1, int n2) {
        if (node == null)
            return null;

        // If either n1 or n2 matches with root's key, report
        // the presence by returning root (Note that if a key is
        // ancestor of other, then the ancestor key becomes LCA
        if (node.data == n1 || node.data == n2)
            return node;

        Node left_lca = findLCA(node.left, n1, n2);
        Node right_lca = findLCA(node.right, n1, n2);

        // If both of the above calls return Non-NULL, then one key
        // is present in once subtree and other is present in other,
        // So this node is the LCA
        if (left_lca != null && right_lca != null)
            return node;

        // Otherwise check if left subtree or right subtree is LCA
        return (left_lca != null) ? left_lca : right_lca;

    }

    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        /*tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        System.out.println("LCA(4, 5) = " +
                tree.findLCA(4, 7).data);
        /*System.out.println("LCA(4, 6) = " +
                tree.findLCA(4, 6).data);
        System.out.println("LCA(3, 4) = " +
                tree.findLCA(3, 4).data);
        System.out.println("LCA(2, 4) = " +
                tree.findLCA(2, 4).data);*/

        tree.root = new Node(10);
        tree.root.left = new Node(10);
        tree.root.right = new Node(19);
        tree.root.left.left = new Node(-5);
        tree.root.right.left = new Node(17);
        tree.root.right.right = new Node(21);
        System.out.println(isBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    public static boolean isBST(Node root, int min, int max) {
        if (root == null) return true;
        if (root.data <= min || root.data > max) {
            return false;
        }
        return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
    }
