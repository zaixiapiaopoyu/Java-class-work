package org.example;

import java.util.Scanner;



public class T_7_18 {
    public static void bubbleSort(double[] numbers) {
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < 10 - i; j++) {
                if (numbers[j - 1] - numbers[j] > 0) {
                    double temp = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        double[] numbers = new double[10];
        Scanner input = new Scanner(System.in);
        System.out.print("Enter  10 numbers: ");
        for (int i = 0; i < 10; i++) {
            numbers[i] = input.nextDouble();
        }
        bubbleSort(numbers);
        System.out.print("The sorted array is: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(numbers[i]);
            System.out.print(" ");
        }
    }
}
//1 2 3.5 6 7 8 9 4.3 1.5 2.6