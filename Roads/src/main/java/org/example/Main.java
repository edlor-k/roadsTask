package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Программа симуляции автомобильного трафика");
        System.out.println("Для использования задайте параметры симуляции.");

        int lanesCount = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Введите количество полос в одну сторону (1-5): ");
            String input = sc.nextLine();
            try {
                lanesCount = Integer.parseInt(input);
                if (lanesCount >= 1 && lanesCount <= 5) {
                    validInput = true;
                } else {
                    System.out.println("Введенное число не является корректным");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число!");
            }
        }

        double chance = 0.0;
        validInput = false;
        while (!validInput) {
            System.out.print("Введите шанс появления авто (0.2 - 0.8): ");
            String input = sc.nextLine();
            try {
                chance = Double.parseDouble(input);
                if (chance >= 0.2 && chance <= 0.8) {
                    validInput = true;
                } else {
                    System.out.println("Введенное число не является корректным");
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите корректное число");
            }
        }

        int speed = 0;
        validInput = false;
        while (!validInput) {
            System.out.print("Введите скорость автомобиля (1-3): ");
            String input = sc.nextLine();
            try {
                speed = Integer.parseInt(input);
                if (speed >= 1 && speed <= 3) {
                    validInput = true;
                } else {
                    System.out.println("Введенное число не является корректным");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число!");
            }
        }
        int time = 0;
        validInput = false;
        while (!validInput) {
            System.out.print("Введите время симуляции (в секундах): ");
            String input = sc.nextLine();
            try {
                time = Integer.parseInt(input);
                if (time >= 1) {
                    validInput = true;
                } else {
                    System.out.println("Число должно быть положительным!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число!");
            }
        }

        Road road = new Road(lanesCount, speed, chance, time);
        road.simulateRoad();

        System.out.println("Симуляция завершена.");
    }
}