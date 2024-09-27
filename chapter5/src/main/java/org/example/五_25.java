package org.example;

import static java.lang.Math.pow;

public class 五_25 {
    public static void main(String[] args) {
        for (int i = 10000; i <=100000 ; i+=10000) {
            double sum = 0;
            for (int j = 1 ; j <= i ; j++) {
                sum = sum + (pow(-1,j+1)/(2*j-1));
            }
            System.out.println(sum*4);
        }
    }
}
