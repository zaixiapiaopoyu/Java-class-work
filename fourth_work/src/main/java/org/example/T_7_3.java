package org.example;

import java.util.Scanner;

public class T_7_3 {
    public static void main(String[] args) {
        int[] numbers = new int[1000];
        int[] counts = new int[104];
        System.out.print("Enter the integers between 1 and 100: ");
        Scanner input = new Scanner(System.in);
        numbers[0] = input.nextInt();
        int i = 0;
        counts[numbers[0]]++;
        while (numbers[i] != 0) {
            i++;
            numbers[i] = input.nextInt();
            counts[numbers[i]]++;
        }
        for (int j = 1; j < 101; j++) {
            if(counts[j] != 0){
                if (counts[j] > 1){
                    System.out.println(j + " occurs " + counts[j] + " times");
                }
                else {
                    System.out.println(j + " occurs " + counts[j] + " time");
                }
            }
        }
    }
}
//2 5 6 5 4 3 23 43 2 100 0