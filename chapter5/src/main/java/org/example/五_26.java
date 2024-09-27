package org.example;

public class 五_26 {
    public static void main(String[] args) {
        for (int i = 10000; i <= 100000; i += 10000) {
//            double sum = 0;
//            int jie = 1;
//            for (int j = 1; j <= 20; j++) {
//                sum += 1.0/jie;
//                jie = jie*j;
//            }
//            System.out.println(i/10000 + " : " + sum);
            double sum = 1.0/i;
            for (int j = i-1; j > 0; j-- ){
                sum = (sum + 1.0)/j;
            }
            System.out.println(sum + 1);
        }
    }
}
