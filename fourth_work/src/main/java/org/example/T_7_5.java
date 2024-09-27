package org.example;


import java.util.Scanner;

public class T_7_5 {
    public static void main(String[] args) {
        int[] nums = new int[11];
        Scanner input = new Scanner(System.in);
        System.out.print("Enter  10 numbers: ");
        int j = 0;
        for (int i = 0; i < 10; i++) {
            int num = input.nextInt();
            int d = 0;
            for (int k = 0; k < j; k++) {
                if (num == nums[k]) d = 1;
            }
            if (d == 0) {
                nums[j] = num;
                j++;
            }
        }
        System.out.println("The number of distinct numbers is: " + j);
        System.out.print("The distinct numbers are: " );
        for (int i = 0; i < j; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}

//1 2 3 2 1 6 3 4 5 2