package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class 五_38 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = input.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        int b = 0;
        int k = 0;
        if(number == 0) {
            k++;
            list.add(b);
        }
        while (number != 0) {
            b = number % 8;
            number = number / 8;
            list.add(b);
            k++;
        }
        System.out.print("Binary : ");
        for (int i = k-1; i >= 0; i--) {
            System.out.print(list.get(i));
        }
    }
}
