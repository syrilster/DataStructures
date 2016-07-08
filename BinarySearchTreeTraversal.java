package DataStructures;

import java.util.*;
import java.util.Stack;

/**
 * Created by Syril on 19-03-2016.
 */
public class BinarySearchTreeTraversal {

    static void preOrderTraversal(BinarySearchTree.Node root) {
        if (root == null)
            return;
        java.util.Stack stack = new Stack();
        while (true) {
            while (root != null) {
                stack.push(root);
                System.out.println(root.data);
                root = root.getLeft();
            }

            if (stack.isEmpty())
                break;
            root = (BinarySearchTree.Node) stack.pop();
            root = root.getRight();
        }
    }


    static Integer[] inOrderTraversal(BinarySearchTree.Node root) {
        Integer[] array = new Integer[100];
        int i = 0;
        if (root == null)
            return new Integer[0];

        Stack stack = new Stack();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }

            if (stack.isEmpty())
                break;
            root = (BinarySearchTree.Node) stack.pop();
            array[i++] = root.getData();
            root = root.getRight();
        }

        for (int j = 0; j < i; j++) {
            System.out.print(" " + array[j]);
        }

        return array;

    }

    static Integer[] postOrderTraversal(BinarySearchTree.Node root) {
        Integer[] array = new Integer[100];
        int i = 0;
        if (root == null)
            return new Integer[0];
        Stack<BinarySearchTree.Node> stack = new Stack<>();
        Stack<BinarySearchTree.Node> output = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinarySearchTree.Node current = stack.peek();
            output.push(current);
            stack.pop();
            if (current.getLeft() != null)
                stack.push(current.getLeft());
            if (current.getRight() != null)
                stack.push(current.getRight());
        }

        while (!output.isEmpty()) {
            array[i] = output.pop().getData();
            i++;
        }

        for (int j = 0; j < i; j++) {
            System.out.println(array[j]);
        }

        return array;
    }

    static void levelOrderTraversal(BinarySearchTree.Node root) {
        if (root == null)
            return;
        Queue queue = new LinkedList();
        BinarySearchTree.Node temp;
        queue.add(root);
        while (!queue.isEmpty()) {
            temp = (BinarySearchTree.Node) queue.remove();
            System.out.print(temp.getData() + " ");
            if (temp.getLeft() != null)
                queue.add(temp.getLeft());
            if (temp.getRight() != null)
                queue.add(temp.getRight());
        }
    }

    static void levelOrderTraversalReverse(BinarySearchTree.Node root) {
        if (root == null)
            return;
        Queue queue = new LinkedList();
        Stack stack = new Stack();
        BinarySearchTree.Node temp;
        queue.add(root);
        while (!queue.isEmpty()) {
            temp = (BinarySearchTree.Node) queue.remove();
            if (temp.getRight() != null)
                queue.add(temp.getRight());
            if (temp.getLeft() != null)
                queue.add(temp.getLeft());
            stack.add(temp.data);
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }


    static BinarySearchTree.Node findMax(BinarySearchTree.Node root) {
        if (root == null) return root;
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root;
    }

    static BinarySearchTree.Node findMin(BinarySearchTree.Node root) {
        if (root == null) return root;
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }

    static BinarySearchTree.Node delete(BinarySearchTree.Node root, int data) {

        if (data < root.getData())
            root.left = delete(root.getLeft(), data);
        else if (data > root.getData())
            root.right = delete(root.getRight(), data);
        else if (root.left != null && root.right != null) // Two children
        {
            root.data = findMin(root.right).data;
            root.right = delete(root.right, root.data);
        } else
            root = (root.left != null) ? root.left : root.right;
        return root;
    }

    static boolean binaryTreeSearch(BinarySearchTree.Node root, int data) {
        BinarySearchTree.Node temp;
        if (root == null)
            return false;
        Queue<BinarySearchTree.Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            temp = queue.remove();
            if (data == temp.getData())
                return true;
            if (temp.getLeft() != null)
                queue.add(temp.getLeft());
            if (temp.getRight() != null)
                queue.add(temp.getRight());
        }

        return false;
    }

    static void verticalOrderTraversal(BinarySearchTree.Node root) {
        if (root == null)
            return;
        Queue queue = new LinkedList();
        BinarySearchTree.Node temp;
        queue.add(root);
        TreeMap<Integer, String> map = new TreeMap<>();
        int level = 0;
        while (!queue.isEmpty()) {
            temp = (BinarySearchTree.Node) queue.remove();
            level = temp.level;

            if (map.get(level) != null) {
                map.put(level, map.get(level) + " " + temp.getData());
            } else {
                map.put(level, String.valueOf(temp.data));
            }

            if (temp.getLeft() != null) {
                temp.getLeft().level = temp.level - 1;
                queue.add(temp.getLeft());
            }

            if (temp.getRight() != null) {
                temp.getRight().level = temp.level + 1;
                queue.add(temp.getRight());
            }

        }

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }

    static void verticalOrderTraversalSum(BinarySearchTree.Node root, TreeMap<Integer, String> map) {
        if (root == null)
            return;
        Queue queue = new LinkedList();
        BinarySearchTree.Node temp;
        queue.add(root);
        while (!queue.isEmpty()) {
            temp = (BinarySearchTree.Node) queue.remove();

            if (map.get(temp.level) != null) {
                map.put(temp.level, String.valueOf(Integer.parseInt(map.get(temp.level)) + temp.getData()));
            } else {
                map.put(temp.level, String.valueOf(temp.data));
            }

            if (temp.getLeft() != null) {
                temp.getLeft().level = temp.level - 1;
                queue.add(temp.getLeft());
            }

            if (temp.getRight() != null) {
                temp.getRight().level = temp.level + 1;
                queue.add(temp.getRight());
            }

        }
    }


    /**
     * The idea here is to use a set to have already visited level so that duplicate levels wont be printed and
     * since its level order we always end up printing the top view of tree
     *
     * @param root
     */
    static void topViewOfTree(BinarySearchTree.Node root) {
        if (root == null)
            return;
        Queue queue = new LinkedList();
        BinarySearchTree.Node temp;
        queue.add(root);

        Set<Integer> levelSet = new HashSet<>();
        while (!queue.isEmpty()) {
            temp = (BinarySearchTree.Node) queue.remove();

            if (!levelSet.contains(temp.level)) {
                levelSet.add(temp.level);
                System.out.println(temp.getData());
            }

            if (temp.getLeft() != null) {
                temp.getLeft().level = temp.level - 1;
                queue.add(temp.getLeft());
            }

            if (temp.getRight() != null) {
                temp.getRight().level = temp.level + 1;
                queue.add(temp.getRight());
            }

        }
    }

    static void bottomViewOfTree(BinarySearchTree.Node root) {
        if (root == null)
            return;
        Queue queue = new LinkedList();
        BinarySearchTree.Node temp;
        queue.add(root);

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int level = 0;
        while (!queue.isEmpty()) {
            temp = (BinarySearchTree.Node) queue.remove();
            level = temp.level;

            map.put(level, temp.data);

            if (temp.getLeft() != null) {
                temp.getLeft().level = temp.level - 1;
                queue.add(temp.getLeft());
            }

            if (temp.getRight() != null) {
                temp.getRight().level = temp.level + 1;
                queue.add(temp.getRight());
            }

        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }

    static void BSTToDLL(BinarySearchTree.Node root) {
        if (root == null)
            return;
        Queue<BinarySearchTree.Node> queue = new LinkedList<>();
        queue.add(root);
        BinarySearchTree.Node node = null;
        BinarySearchTree.Node last = null;
        while (!queue.isEmpty()) {
            node = queue.peek();
            queue.remove();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            node.right = queue.peek();
            node.left = last;
            last = node;
        }

    }

    // Idea here is to extend search in BST to include both n1 and n2 and recur.
    static int lowestCommonAncestor(BinarySearchTree.Node root, int n1, int n2) {
        if (root == null)
            return 0;
        if (n1 < root.data && n2 < root.data)
            return lowestCommonAncestor(root.left, n1, n2);
        else if (n1 > root.data && n2 > root.data)
            return lowestCommonAncestor(root.right, n1, n2);
        else
            return root.data;
    }


    //Using two stack will solve the issue. Just observe the push order in stack s1 and s2
    static void binaryTreeZigZagPrint(BinarySearchTree.Node root) {
        if (root == null)
            return;
        Stack<BinarySearchTree.Node> s1 = new Stack();
        Stack<BinarySearchTree.Node> s2 = new Stack();
        s1.push(root);

        while (!s1.isEmpty() || !s2.isEmpty()) {
            while (!s1.isEmpty()) {
                BinarySearchTree.Node temp = s1.peek();
                s1.pop();
                System.out.print(temp.data + " ");

                // Note that is right is pushed before left
                if (temp.right != null) {
                    s2.push(temp.right);
                }
                if (temp.left != null)
                    s2.push(temp.left);
            }

            while (!s2.isEmpty()) {
                BinarySearchTree.Node temp = s2.peek();
                s2.pop();
                System.out.print(temp.data + " ");

                // Note that is left is pushed before right
                if (temp.left != null) {
                    s1.push(temp.left);
                }
                if (temp.right != null)
                    s1.push(temp.right);
            }
        }
    }


    //This will also sort the BST in Descending Order
    static void mirror(BinarySearchTree.Node node) {
        if (node == null)
            return;

        BinarySearchTree.Node temp;
        mirror(node.left);
        mirror(node.right);

        temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    static int maxDepth(BinarySearchTree.Node node) {
        if (node == null)
            return 0;
        int lDepth = maxDepth(node.getLeft());
        int rDepth = maxDepth(node.getRight());

        if (lDepth > rDepth) {
            return lDepth + 1;
        } else {
            return rDepth + 1;
        }
    }

    public static void MorrisInOrder(BinarySearchTree.Node root) {
        if (root == null) return;
        BinarySearchTree.Node current = root;
        while (current != null) {
            if (current.left == null) {
                System.out.print(current.data + " ");
                current = current.right;
            } else {
                BinarySearchTree.Node predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    System.out.print(current.data + " ");
                    current = current.right;
                }
            }
        }
    }

}


