package edu.wmich.cs3310.hw4.main;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Sorting {
    public static void main(String[] args) {
        long[] time = new long[6];
        int[] size = new int[]{1, 10, 100, 1000, 10000, 100000};
        for (int i = 0; i < size.length; i++) {
            int s = size[i];
            int[] datal = new int[s];
            for (int k = 0; k < s; k++) {
                datal[k] = k;
            }
            shuffle(datal);
            MedianQueue.sortUsingMedians(datal);
            for (int y = 0; y < 5; y++) {
                int[] data = new int[s];
                for (int k = 0; k < s; k++) {
                    data[k] = k;
                }
                shuffle(data);
                long t = System.nanoTime();
                int[] sortedData = MedianQueue.sortUsingMedians(data);
                time[i] = time[i] + (System.nanoTime() - t);
                System.out.println(s + " " + y);
            }
        }
        for (long l : time) {
            System.out.print((l / 5) + " ");
        }
        System.out.println();

    }

    static void shuffle(int[] array) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
}

