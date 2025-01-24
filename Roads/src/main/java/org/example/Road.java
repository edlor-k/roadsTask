package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Road {
    ArrayList<ArrayList<Character>> road;
    Random rand = new Random();
    private static final char emptySign = ' ';
    private static final char carSign = '▉';
    private static final char border = '—';
    private static final int size = 40;
    private static final String separator = "\n\n\n";

    private int lanesCount;
    private int speed;
    private double chance;
    private int simulationTime;

    public Road(int lanesCount, int speed, double chance, int simulationTime) {
        this.lanesCount = lanesCount;
        this.speed = speed;
        this.chance = chance;
        this.simulationTime = simulationTime * 1000;
        road = new ArrayList<>(lanesCount * 2);
        for (int i = 0; i < lanesCount * 2; i++) {
            road.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                road.get(i).add(emptySign);
            }
        }
    }

    public void showRoad() {
        printBorder();

        for (int i = 0; i < lanesCount; i++) {
            printLane(road.get(i));
            printBorder();
        }

        printBorder();

        for (int i = lanesCount; i < lanesCount * 2; i++) {
            printLane(road.get(i));
            printBorder();
        }

        System.out.print(separator);
    }

    public void printBorder() {
        for (int i = 0; i < size; i++)
            System.out.print(border);
        System.out.println();
    }

    public void printLane(ArrayList<Character> lane) {
        for (char c : lane) {
            System.out.print(c);
        }
        System.out.println();
    }

    public void simulateRoad() {
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        while (endTime - startTime < simulationTime) {
            showRoad();
            for (int i = 0; i < lanesCount; i++) {
                for (int j = size-1; j >= 0; j--) {
                    if (road.get(i).get(j) == carSign) {
                        road.get(i).set(j, emptySign);
                        if (j + speed < size)
                            road.get(i).set(j + speed, carSign);
                    }
                }
                if (rand.nextDouble() <= chance) {
                    road.get(i).set(0, carSign);
                }
            }
            for (int i = lanesCount; i < lanesCount * 2; i++) {
                rand = new Random();
                for (int j = 0; j < size; j++) {
                    if (road.get(i).get(j) == carSign) {
                        road.get(i).set(j, emptySign);
                        if (j - speed >= 0)
                            road.get(i).set(j - speed, carSign);
                    }
                }
                if (rand.nextDouble() <= chance) {
                    road.get(i).set(size-1, carSign);
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            endTime = System.currentTimeMillis();
        }
    }
}
