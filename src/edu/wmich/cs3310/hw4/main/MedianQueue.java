package edu.wmich.cs3310.hw4.main;

/**
 * Implementation of median queue
 */

public class MedianQueue {
    // private int median;
    private SortedLinkedList Qplus;
    private ArrayHeap Qminus;
    private int count = 0;
    private int lastDelete;

    /**
     * Constructor
     */
    public MedianQueue() {
        Qplus = new SortedLinkedList();
        Qminus = new ArrayHeap();
        lastDelete = 0;
    }

    /**
     * Sort data using MedianQueue class
     *
     * @param data unsorted data
     * @return sorted data;
     */
    public static int[] sortUsingMedians(int[] data) {
        MedianQueue queue = new MedianQueue();
        for (int i : data) {
            queue.add(i);
        }
        int size = queue.count;
        int[] res = new int[size];
        int m = (size / 2);
        if (size % 2 == 0) {
            m = m - 1;
        }
        for (int i = m; i > -1; i--) {
            res[i] = queue.Qminus.removeMax();
        }
        for (int i = (m + 1); i < queue.count; i++) {
            res[i] = queue.Qplus.removeMin();
        }
        return res;
    }

    /**
     * Add value to queue
     *
     * @param v value
     */
    public boolean add(int v) {
        if (count == 0) {
            Qplus.add(v);
            count++;
            return true;
        }
        Qplus.add(v);
        count++;
        if (count % 2 == 0) {
            Qminus.add(Qplus.removeMin());
            return false;
        }
        return true;
    }

    /**
     * @return median value of object
     */
    public int getMedian() {
        int y = Qminus.size() - Qplus.size();
        if (y == 0) {
            int gj=Qminus.getMax();
            int j=Qplus.getMin();
            return ((j+gj)/ 2);
        } else {
            return Qplus.getMin();
        }

    }

    /**
     * Delete median value
     *
     * @return deleted median
     */
    public int deleteMedian() {
        if (count == 0) {
            lastDelete = 0;
            return 0;
        }
        count--;
        if (count % 2 == 0) {
            lastDelete = 1;
            return Qplus.removeMin();

        } else {
            lastDelete = -1;
            return Qminus.removeMax();
        }
    }

    /**
     * @return string representation
     */
    @Override
    public String toString() {
        if (count == 0) {
            return "Q- {null} Q+ {null}";
        }
        String pref = "Q- {" + Qminus;
        if (((Qminus.size() - Qplus.size()) == 0)) {
            if (Qplus.size() > 0) {
                return pref + "} Q+ {" + " " + Qplus + "}";
            } else {
                return pref + "} Q+ { null }";
            }
        } else {
            if (Qminus.size() > 0) {
                return pref + " } Q+ {" + Qplus + "}";
            } else {
                return "Q- {null} Q+ {" + Qplus + "}";
            }
        }
    }

    public String inorder() {
        return "Inorder traversal of Q- is: " + Qminus.toString() + "\n" +
                "Inorder traversal of Q+ is: "+Qplus.toString();
    }

    public int lastDelete() {
        return lastDelete;
    }
}
