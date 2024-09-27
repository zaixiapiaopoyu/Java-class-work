package org.example;

public class T_7_23 {
    public static void main(String[] args) {
        int[] guizi = new int[101];
        for (int i = 1; i <= 100; i+=2) {
            guizi[i] = 1;
        }
        for (int i = 3; i <= 100; i++) {
            for (int j = i; j <= 100; j+=i) {
                if (guizi[j]%2 == 0) {
                    guizi[j] = 1;
                }
                else {
                    guizi[j] = 0;
                }
            }
        }
        for (int i = 1; i <= 100; i++) {
            if (guizi[i]==1) {
                System.out.println(i);
            }
        }
    }
}
