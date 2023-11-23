// Version 1.0 by Mattan Elkaim
// See https://github.com/mattanelkaim/LinkedListUtils

public class LinkedListUtils {
/* --------------------------------------------------- GENERATING --------------------------------------------------- */

    public static Node<Integer> buildRandom(int size, int min, int max) {
        // Time complexity: O(n)
        Node<Integer> head = null;
        for (int i = 0; i < size; i++) {
            int value = (int)(Math.random() * (max - min + 1)) + min;
            head = new Node<>(value, head);
        }

        return head;
    }

    public static Node<Integer> reverseArrayToList(int[] arr) {
        // Time complexity: O(n)
        Node<Integer> head = null;
        for (int current : arr) {
            head = new Node<>(current, head);
        }
        return head;
    }

    public static Node<Integer> arrayToList(int[] arr) {
        // Time complexity: O(n)
        Node<Integer> head = null;
        // Reversing from tail to head --> normal list
        for (int i = arr.length - 1; i >= 0; i--) {
            head = new Node<>(arr[i], head);
        }
        return head;
    }


/* -------------------------------------------------- MANIPULATION -------------------------------------------------- */

    public static Node<Integer> insert(Node<Integer> head, int num) {
        // Time complexity: O(n)
        if (head == null)
            return new Node<>(num);
        getTail(head).setNext(new Node<>(num));
        return head;
    }

    public static Node<Integer> insertHead(Node<Integer> head, int num) {
        // Time complexity: O(n)
        return new Node<>(num, head);
    }

    // TODO optimize current.getNext()
    public static Node<Integer> insertInOrder(Node<Integer> head, int num) {
        // Time complexity: O(n)
        if (head == null) // Edge case empty list
            return new Node<>(num);
        // Edge case insert in head
        if (head.getValue() >= num) {
            return insertHead(head, num);
        }

        // Insert in body
        Node<Integer> current = head;
        while (current.getNext() != null && current.getNext().getValue() < num) {
            current = current.getNext();
        }

        current.setNext(new Node<>(num, current.getNext()));
        return head;
    }

    public static Node<Integer> remove(Node<Integer> head, int num) {
        // Time complexity: O(n)
        if (head != null && head.getValue() == num) { // First remove head
            return head.getNext();
        }

        // Then remove body
        Node<Integer> prev = null;
        Node<Integer> current = head;

        while (current != null) {
            if (current.getValue() == num) { // Remove
                prev.setNext(current.getNext());
                break;
            }
            else
                prev = current;

            current = current.getNext();
        }

        return head;
    }

    public static Node<Integer> removeAll(Node<Integer> head, int num) {
        // Time complexity: O(n)
        while (head != null && head.getValue() == num) { // First remove head(s)
            head = head.getNext();
        }

        // Then remove body
        Node<Integer> prev = null;
        Node<Integer> current = head;

        while (current != null) {
            if (current.getValue() == num) // Remove
                prev.setNext(current.getNext());
            else
                prev = current;

            current = current.getNext();
        }

        return head;
    }


/* ----------------------------------------------------- ACCESS ----------------------------------------------------- */

    public static Node<Integer> getTail(Node<Integer> head) {
        // Time complexity: O(n)
        if (head == null)
            return null;

        while (head.getNext() != null)
            head = head.getNext();

        return head;
    }

    public static Node<Integer> maxNode(Node<Integer> head) {
        // Time complexity: O(n)
        if (head == null)
            return null;

        return search(head, max(head));
    }

    public static Node<Integer> search(Node<Integer> head, int num) {
        // Time complexity: O(n)
        while (head != null) {
            if (head.getValue() == num)
                return head;
            head = head.getNext();
        }

        return null; // Not found
    }

    // TODO optimize remove i
    public static Node<Integer> elementAt(Node<Integer> head, int index) {
        // Time complexity: O(n)
        if (index < 0)
            return null;

        for (int i = 0; i < index; i++) {
            if (head == null)
                return null;
            head = head.getNext();
        }

        return head;
    }


/* ----------------------------------------------------- CHECKS ----------------------------------------------------- */

    public static boolean isExist(Node<Integer> head, int value) {
        // Time complexity: O(n)
        return search(head, value) != null;
    }

    public static boolean isAllEven(Node<Integer> head) {
        // Time complexity: O(n)
        while (head != null) {
            if (head.getValue() % 2 == 1)
                return false;
            head = head.getNext();
        }

        return true; // Also true when null
    }

    public static boolean isAllSame(Node<Integer> head) {
        // Time complexity: O(n)
        if (head == null)
            return true; // Edge case

        int val = head.getValue();
        head = head.getNext();

        while (head != null) {
            if (head.getValue() != val)
                return false;
            head = head.getNext();
        }

        return true;
    }

    // TODO optimize current.getNext()
    public static boolean isAscending(Node<Integer> head) {
        // Time complexity: O(n)
        if (head == null)
            return true;

        while (head.getNext() != null) {
            if (head.getValue() > head.getNext().getValue())
                return false;
            head = head.getNext();
        }

        return true;
    }


/* -------------------------------------------------- CALCULATIONS -------------------------------------------------- */

    public static int length(Node<Integer> head) {
        // Time complexity: O(n)
        int len = 0;
        while (head != null) {
            len++;
            head = head.getNext();
        }

        return len;
    }

    public static int sum(Node<Integer> head) {
        // Time complexity: O(n)
        int sum = 0;
        while (head != null) {
            sum += head.getValue();
            head = head.getNext();
        }

        return sum;
    }

    public static int count(Node<Integer> head, int num) {
        // Time complexity: O(n)
        int occurrences = 0;
        while (head != null) {
            if (head.getValue() == num)
                occurrences++;
            head = head.getNext();
        }

        return occurrences;
    }

    public static int max(Node<Integer> head) {
        // Time complexity: O(n)
        if (head == null)
            throw new IllegalArgumentException("List cannot be empty!");
        // In case of null, an exception will be thrown

        int max = head.getValue();
        head = head.getNext(); // Skip redundant check

        while (head != null) {
            if (head.getValue() > max)
                max = head.getValue();
            head = head.getNext();
        }

        return max;
    }

    public static int iMax(Node<Integer> head) {
        // Time complexity: O(n)
        // Return index of max element
        if (head == null)
            throw new IllegalArgumentException("List cannot be empty!");
        return index(head, max(head));
    }

    public static int index(Node<Integer> head, int num) {
        // Time complexity: O(n)
        int pos = 0;
        while (head != null) {
            if (head.getValue() == num)
                return pos;
            head = head.getNext();
            pos++;
        }

        return -1; // Not found
    }


/* -------------------------------------------------- VISUALIZATION ------------------------------------------------- */

    public static void print(Node<Integer> head) {
        // Time complexity: O(n)
        if (head == null) {
            System.out.println("null");
            return;
        }
        System.out.print(head.getValue() + "--->");
        print(head.getNext());
    }

    private static void printRev(Node<Integer> head) {
        // Time complexity: O(n)
        if (head == null) {
            System.out.print("null");
            return;
        }
        printRev(head.getNext());
        System.out.print("<---" + head.getValue());
    }

    public static void printReverse(Node<Integer> head) {
        // Time complexity: O(n)
        printRev(head);
        System.out.println();
    }
}
