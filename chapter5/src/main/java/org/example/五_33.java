package org.example;

public class 五_33 {
    public static void main(String[] args) {
        System.out.println("Perfect Numbers:");
        for (int i = 2; i <= 10000; i++) {
            int sum = 0;
            for (int j = 1; j < i; j++) {
                if(i%j == 0) sum += j;
            }
            if(sum == i) System.out.println(i);
        }
    }
}
