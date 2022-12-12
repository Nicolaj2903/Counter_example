package org.openjfx;


import java.util.ArrayList;
import java.util.List;

/**
 * Source:
 * Niels Kjær Faurskovs Youtube series
 */

public class Counter {

    private final int threadLimit = 4;
    private int max = 200_000;
    private int value;
    private Output output;

    public Counter(int max) {
        this.max = max;
    }

    public Counter() {

    }

    public static void main(String[] args) {
        Counter counter = new Counter();
//        Counter counter_400k = new Counter(400_000);
//        counter_500k.startIncrementing();
//        counter_400k.startIncrementing();

        counter.setOutput(new Output() {
            @Override
            public void append(String message) {
                System.out.println(message);
            }
        });
//        System.out.println("\n1: " + counter_500k.getValue());
//        System.out.println("2: " + counter_400k.getValue());
        counter.startIncrementing();
    }

    public int getValue() {
        return value;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void startIncrementing() {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < threadLimit; i++) {
            int finalI = i;

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < max / threadLimit; i++) {
                        increment();
                    }
                    if (output != null) {
                        output.append("Thread " + finalI + " finished counting");
                    }
                }
            });
            threadList.add(thread);
        }
        for (Thread thread : threadList)
            thread.start();

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void increment() {
        value++;
    }

    public void setOutput(Output output) {
        this.output = output;
    }
}
