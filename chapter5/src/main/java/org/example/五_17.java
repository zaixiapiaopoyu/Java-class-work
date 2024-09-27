package org.example;

import java.util.Scanner;

public class 五_17 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        System.out.print("Enter the number of lines: ");
        n = input.nextInt();
        for (int i = 0; i < n; i++) {
            int d = 0;
            for (int j = 0; j < n; j++) {
                if(i >= n - j - 1){
                    if(j != n-1){
                        System.out.print(n-j+" ");
                    }
                    else if (j == n-1){
                        System.out.print(n-j);
                        d = 1;
                    }
                    if(d == 1){
                        for(int k = 2; k <= i+1; k++){
                            System.out.print(" "+k);
                        }
                    }
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
