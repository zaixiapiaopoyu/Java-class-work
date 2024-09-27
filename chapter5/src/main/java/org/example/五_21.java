package org.example;

import java.util.Scanner;

public class 五_21 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Loan Amount: ");
        double loanAmount = input.nextDouble();
        System.out.print("Number of Years: ");
        int years = input.nextInt();
        System.out.println("Interest Rate    Monthly Payment    Total Amount");
        double rate = 4.875;
        for (int i = 0; i <= 24; i++) {
            rate += 0.125;
            double irate = rate / 1200;
            double monthly = loanAmount * irate / (1 - 1 / Math.pow(1 + irate, years * 12));
            double total = monthly * 12 * years;
            System.out.printf("%.3f",rate);
            System.out.print("%");
            System.out.print("           ");
            System.out.printf("%.2f",monthly);
            System.out.print("             ");
            System.out.printf("%.2f",total);
            System.out.println();
        }
    }
}
