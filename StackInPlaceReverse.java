package DataStructures;

/**
 * Created by syrils on 5/2/16.
 */
public class StackInPlaceReverse {

    public static void reverse(java.util.Stack<String> stack) {
        String element = stack.pop();
        if (stack.size() != 1) {
            reverse(stack);
        }
        pushToBottomOfStack(element, stack);
    }


    private static void pushToBottomOfStack(String data, java.util.Stack<String> stack) {
        String element = stack.pop();
        if (stack.size() != 0) {
            pushToBottomOfStack(data, stack);
        } else {
            stack.push(data);
        }
        stack.push(element);
    }

    public static void main(String args[]) {
        java.util.Stack<String> myStack = new java.util.Stack<>();
        myStack.push("A");
        myStack.push("B");
        myStack.push("C");
        myStack.push("D");
        System.out.println("Original Stack : " + myStack);
        StackInPlaceReverse.reverse(myStack);
        System.out.println("Revered Stack : " + myStack);

    }


}
