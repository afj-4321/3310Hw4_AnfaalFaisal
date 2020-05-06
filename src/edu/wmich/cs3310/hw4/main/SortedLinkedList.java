package edu.wmich.cs3310.hw4.main;

/**
 * Sorted list implementation
 */
public class SortedLinkedList {

    private Node head;
    private int count;

    /**
     * Constructor
     */
    public SortedLinkedList() {
        head = null;
        count = 0;
    }

    /**
     * Add value to list
     * @param v value
     */
    public void add(int v) {
        Node node = new Node(v);
        if (head == null) {
            head = node;
        } else {
            if (v <= head.data) {
                node.next = head;
                head.prev = node;
                head = node;
            } else {
                boolean run = true;
                Node tmp = head;
                while (run) {
                    if (tmp.next == null) {
                        tmp.next = node;
                        node.prev = tmp;
                        run = false;
                    } else {
                        if (tmp.next.data <= v) {
                            tmp = tmp.next;
                        } else {
                            node.prev = tmp;
                            node.next = tmp.next;
                            tmp.next.prev = node;
                            tmp.next = node;
                            run = false;
                        }
                    }
                }
            }
        }
        count++;
    }

    /**
     * Remove minimal value
     * @return value
     */
    public int removeMin() {
        if (head == null) {
            throw new NullPointerException();
        }
        int j = head.data;
        head = head.next;
        count--;
        return j;
    }

    /**
     *
     * @return minimal value
     */
    public int getMin() {
        if (head == null) {
            throw new NullPointerException();
        }
        return head.data;
    }

    /**
     *
     * @return size
     */
    public int size() {
        return count;
    }

    /**
     * @return string representation
     */
    @Override
    public String toString() {
        if (head == null) {
            return "null";
        }
        StringBuilder a = new StringBuilder();
        Node tmp = head;
        while (tmp != null) {
            a.append(tmp.data).append(" ");
            tmp = tmp.next;
        }
        return a.toString();
    }

    /**
     * Node class
     * Information holder
     */
    private static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }
    }
}