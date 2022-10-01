/**
 * Obligatory task 2 for course Algorithms and Datastructures
 *
 * @Author Daniel Evensen
 */

import java.util.Date;

public class Recursion {
    public static double exponentialN(double base, int n) {
        if (n == 0) {
            return 1;
        } else if (n > 0){
            return base * exponentialN(base, n - 1);
        } else {
            return 0;
        }



    }

    public static double nExponential(double base, int n) {
        double xPow2 = base * base;
        if (n == 0) {
            return 1;
        } else if (n % 2 != 0) {
            return base * nExponential(xPow2, (n - 1) / 2);
        } else {
            return nExponential(xPow2, n / 2);
        }
    }

    public static double mathPow(double base, int n) {
        if (n == 0) {
            return 1;
        } else {
            return Math.pow(base, n);
        }
    }

    public static void main(String[] args) {
        System.out.println("2^12 = " + exponentialN(2, 12));
        System.out.println("2^12 = " + nExponential(2, 12));
        System.out.println("2^12 = " + mathPow(2, 12));
        System.out.println("2^12 should be 4096\n");

        System.out.println("3^14 = " + exponentialN(3, 14));
        System.out.println("3^14 = " + nExponential(3, 14));
        System.out.println("3^14 = " + mathPow(3, 14));
        System.out.println("3^14 should be 4 782 969\n");

        System.out.println("Runtime tests: \n-------------------------------------------");
        System.out.println("Task 2.1-1");
        runTimeTask1(1.0001, 100);
        runTimeTask1(1.0001, 1000);
        runTimeTask1(1.0001, 10000);;


        System.out.println("\nTask 2.2-3");
        runTimeTask2(1.0001, 100);
        runTimeTask2(1.0001, 1000);
        runTimeTask2(1.0001, 10000);

        System.out.println("\nTask 3, Math.pow:");
        runTimeTask3(1.0001, 100);
        runTimeTask3(1.0001, 1000);
        runTimeTask3(1.0001, 10000);
    }

    public static void runTimeTask1(double base, int n) {
        Date start = new Date();
        int rounds = 0;
        double time;
        Date stop;

        do {
            exponentialN(base, n);
            stop = new Date();
            rounds++;
        } while (stop.getTime() - start.getTime() < 1000);
        time = (double) (stop.getTime() - start.getTime()) / rounds;
        double resultTime = time * 10000000;
        System.out.println("Nanoseconds per round with base = " + base + " & for n = " + n + ": " + resultTime);
    }

    public static void runTimeTask2(double base, int n) {
        Date start = new Date();
        int rounds = 0;
        double time;
        Date stop;

        do {
            nExponential(base, n);
            stop = new Date();
            rounds++;
        } while (stop.getTime() - start.getTime() < 1000);
        time = (double) (stop.getTime() - start.getTime()) / rounds;
        double resultTime = time * 10000000;
        System.out.println("Nanoseconds per round with base = " + base + " & for n = " + n + ": " + resultTime);
    }

    public static void runTimeTask3(double base, int n) {
        Date start = new Date();
        int rounds = 0;
        double time;
        Date stop;

        do {
            mathPow(base, n);
            stop = new Date();
            rounds++;
        } while (stop.getTime() - start.getTime() < 1000);
        time = (double) (stop.getTime() - start.getTime()) / rounds;
        double resultTime = time * 10000000;
        System.out.println("Nanoseconds per round with base = " + base + " & for n = " + n + ": " + resultTime);
    }

}
