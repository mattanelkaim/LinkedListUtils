public class LinkedListUtilsMain {
    public static void main(String[] args) {
        Node<Integer> head = LinkedListUtils.arrayToList(new int[] {1, 2, 3, 4, 10, 5});
        LinkedListUtils.print(head);
        LinkedListUtils.printReverse(head);

        // Use the functions
    }
}
