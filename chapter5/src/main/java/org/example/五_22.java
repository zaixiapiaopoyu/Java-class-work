package org.example;

import java.util.Scanner;

public class 五_22 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Loan Amount: ");
        double loanAmount = input.nextDouble();
        System.out.print("Number of Years: ");
        int years = input.nextInt();
        System.out.print("Annual Interest Rate: ");
        double interestRate = input.nextDouble();
        double irate = interestRate / 1200;
        System.out.println();
        double monthly = loanAmount * irate / (1 - 1 / Math.pow(1 + irate, years * 12));
        double total = monthly * 12 * years;
        System.out.print("Monthly payment: "+(Math.floor(monthly * 100) / 100));
        System.out.println();
        System.out.printf("Total payment: %.2f",total);
        System.out.println();
        System.out.println();
        System.out.println("Payment#     Interest    Principal     Balance");
        double interest;
        double princial = monthly;
        double balance = loanAmount;
        for (int i = 1; i <= years * 12; i++) {
            interest = irate * balance;
            princial = monthly - interest;
            balance = balance - princial;
            System.out.printf("%-13d",i);
            System.out.printf("%-12.2f",(Math.floor(interest * 100) / 100));
            System.out.printf("%-14.2f",(Math.floor(princial * 100) / 100));
            System.out.printf("%.2f",(Math.floor(balance * 100) / 100));
            System.out.println();
        }
    }
}
