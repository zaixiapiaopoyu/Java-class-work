package org.example;

public class 五_27 {
    public static void main(String[] args) {
        int k = 1;
        for (int i = 101; i <= 2100; i++) {
            if(i % 400 == 0 ||( i % 4 == 0 && i % 100 != 0)){
                if(k % 10 != 0){
                    System.out.printf("%-5d",i);
                }
                else{
                    System.out.printf("%-5d",i);
                    System.out.println();
                }
                k++;
            }
        }
        System.out.println();
        System.out.println("number of years : " + (k-1));
    }
}
