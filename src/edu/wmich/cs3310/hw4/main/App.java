package edu.wmich.cs3310.hw4.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Driver app
 */
public class App {
    static MedianQueue q;

    /**
     * Start point
     *
     * @param args file with actions
     */
    public static void main(String[] args) {
        q = new MedianQueue();
        if (args.length == 0) {
            boolean run = true;
            Scanner sc = new Scanner(System.in);
            while (run) {
                System.out.println(">A – add? \n" +
                        ">C – check median? \n" +
                        ">D – delete median? \n" +
                        ">E – exit?");
                System.out.print(">");
                switch (sc.nextLine().toUpperCase()) {
                    case "A":
                        System.out.println("> ok, specify the value v to be inserted into S");
                        System.out.print(">");
                        String bas = sc.nextLine();
                        int bai = Integer.parseInt(bas);
                        if (q.add(bai)) {
                            System.out.println("done, value " + bas + " is inserted in Q-");
                        } else {
                            System.out.println("done, value " + bas + " is inserted in Q+");
                        }
                        System.out.println(q.inorder());
                        break;
                    case "E":
                        run = false;
                        System.out.println("exit program");
                        System.exit(0);
                        break;
                    case "C":
                        System.out.println(">the median is " + q.getMedian());
                        System.out.println(q.inorder());
                        break;
                    case "D":
                        int median=q.deleteMedian();
                        if (q.lastDelete()<0){
                            System.out.println(">lower median "+median+" is deleted from Q-.");
                        } else {
                            System.out.println(">median "+median+" is deleted from Q+.");
                        }
                        System.out.println(q.inorder());
                        break;
                    default:
                        System.out.println(">unknown action");
                        break;
                }
            }

        } else {
            long[] time = new long[3];
            int[] counts = new int[3];
            String[] array = new String[0];
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
                StringBuilder k = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    k.append(line).append(",");
                }
                array = k.toString().split(",");
            } catch (Exception e) {
                e.printStackTrace();
            }
            long tm;
            for (String s : array) {
                switch (s.charAt(0)) {
                    case 'a':
                        int adding = Integer.parseInt(s.split(" ")[1]);
                        tm = System.nanoTime();
                        q.add(adding);
                        time[0] = time[0] + System.nanoTime() - tm;
                        counts[0]++;
                        break;
                    case 'g':
                        int m;
                        tm = System.nanoTime();
                        m = q.getMedian();
                        time[1] = time[1] + System.nanoTime() - tm;
                        counts[1]++;
                        System.out.println(m);
                        break;
                    case 'd':
                        int dm;
                        tm = System.nanoTime();
                        dm = q.deleteMedian();
                        time[2] = time[2] + System.nanoTime() - tm;
                        counts[2]++;
                        break;
                }
            }
            for (int i = 0; i < 3; i++) {
                System.out.print((time[i] / counts[i]) + " ");
            }
            System.out.println();
            for (int i = 0; i < 3; i++) {
                System.out.print((counts[i]) + " ");
            }
            System.out.println();
        }
    }
}
