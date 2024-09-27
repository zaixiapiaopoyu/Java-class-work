package org.example;

import java.util.Scanner;

public class 五_45 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter 10 numbers: ");
        double[] arr = new double[10];
        double sum = 0;
        for (int i = 0; i < 10; i++) {
            arr[i] = input.nextDouble();
            sum += arr[i];
        }
        double mean = sum / 10;
        System.out.printf("The mean is %.2f",mean);
        System.out.println();
        double x1 = 0;
        double x2 = 0;
        for (int i = 0; i < 10; i++) {
            x1 += arr[i]*arr[i];
            x2 += arr[i];
        }
        x2 = x2*x2;
        double devision;
        devision = Math.sqrt((x1-x2/10)/9);
        System.out.printf("The standard deviation is %.5f",devision);

    }
}
//1 2 3 4.5 5.6 6 7 8 9 10