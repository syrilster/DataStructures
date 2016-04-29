static void detectLoopAndRemove(LinkedList.Node node) {
        boolean isLooped;
        LinkedList.Node slow, fast;
        slow = fast = node;
        while (true) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast.data == null || fast.next.data == null) {
                return;
            } else if (fast.data == slow.data) {
                isLooped = true;
                break;
            }


        }

        //Find Starting point of loop
        if (isLooped) {
            fast = node;

            while (fast.data != slow.data) {
                fast = fast.next;
                slow = slow.next;
            }

            System.out.println("Starting point of loop is " + fast.data);
        } else {
            System.out.println("No Loop Detected");
        }


        //Let one pointer be at the loop starting and other pointer at a place after the loop start and check the 
        // below condition
        fast = fast.next;
        while (fast.next.data != slow.data) {
            fast = fast.next;
        }

        fast.next = null;

    }
