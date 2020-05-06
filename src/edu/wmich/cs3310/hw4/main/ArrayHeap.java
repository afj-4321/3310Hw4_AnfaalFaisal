package edu.wmich.cs3310.hw4.main;

/**
 * Array heap implementation
 */
public class ArrayHeap {
    private int[] array;
    private int count = 0;

    /**
     * Constructor
     */
    public ArrayHeap() {
        array = new int[20];
    }

    /**
     * Add value to heap
     *
     * @param v value
     */
    public void add(int v) {
        if (count == 0) {
            array[0] = v;
            count++;
            return;
        }
        int y = 0;
        for (int i = 0; i < count; i++) {
            if (array[i] <= v) {
                y = i + 1;
            } else {
                i = count + 1;
            }
        }
        if (count - y >= 0) System.arraycopy(array, y, array, y + 1, count - y);
        array[y] = v;
        count++;
        if (count == array.length) {
            int[] newCount = new int[array.length * 2];
            System.arraycopy(array, 0, newCount, 0, array.length);
            array = newCount;
        }
    }

    /**
     * Remove maximal value
     *
     * @return value
     */
    public int removeMax() {
        count--;
        return array[count];
    }

    /**
     *
     * @return maximal value
     */
    public int getMax() {
        return array[count-1];
    }

    /**
     *
     * @return size of heap
     */
    public int size() {
        return count;
    }

    /**
     * @return string representation
     */
    @Override
    public String toString() {
        if (count == 0) {
            return "null";
        }
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < count; i++) {
            a.append(array[i]).append(" ");
        }
        return a.toString();
    }


}
