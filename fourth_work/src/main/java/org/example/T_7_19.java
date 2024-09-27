package org.example;

import java.util.Scanner;

public class T_7_19 {
    public static boolean isSorted(int n,int[] list) {
        for (int i = 1; i < n; i++) {
            if (list[i] < list[i - 1]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the size of the list: ");
        n = input.nextInt();
        int[] list = new int[n];
        System.out.print("Enter the contents of the list: ");
        for (int i = 0; i < n; i++) {
            list[i] = input.nextInt();
        }
        System.out.print("The  list has " + n + "integers ");
        for (int i = 0; i < n; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
        if (isSorted(n, list)) {
            System.out.println("The list is already sorted ");
        }
        else {
            System.out.println("The list is not sorted ");
        }
    }
}
