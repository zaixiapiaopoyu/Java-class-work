package org.example;

import static java.lang.Math.pow;

public class 五_19 {
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7-i; j++) {
                System.out.print("    ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.printf("%4d",(int)pow(2,j));
            }
            for (int j = i-1; j >= 0; j--) {
                System.out.printf("%4d",(int)pow(2,j));
            }
            System.out.println();
        }
    }
}
