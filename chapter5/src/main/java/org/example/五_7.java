package org.example;

public class 五_7 {
    public static void main(String[] args) {
        double money = 10000;
        double temp;
        for (int i = 0; i < 10; i++) {
            temp = money * 0.05;
            money += temp;
        }
        System.out.println("10年后的学费: " + money);
        double sum=0;
        for (int i = 0; i < 4; i++) {
            sum += money;
            temp = money * 0.05;
            money += temp;
        }
        System.out.println("4年总学费为：" + sum);
    }
}
