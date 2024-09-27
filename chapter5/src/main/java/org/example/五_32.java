package org.example;

import java.util.Scanner;

public class 五_32 {
    public static void main(String[] args) {
        int lottery;
        int one;
        int two;
        one = (int)(Math.random()*10);
        two = (int)(Math.random()*10);
        while(one == two){
            two = (int)(Math.random()*10);
        }
        lottery = one*10 + two;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your lottery pick (two digits): ");
        int guess = input.nextInt();
        int lotteryDigit1 = one;
        int lotteryDigit2 = two;
        int guessDigit1 = guess / 10;
        int guessDigit2 = guess % 10;
        System.out.println("The lottery number is " + lottery);
        if(guess == lottery) System.out.println("Exact match: you win $10,000");
        else if (guessDigit2 == lotteryDigit1 && guessDigit1 == lotteryDigit2) System.out.println("Match all digits: you win $3,000");
        else if (guessDigit2 == lotteryDigit1 || guessDigit2 == lotteryDigit2 || guessDigit1 == lotteryDigit2 || guessDigit1 == lotteryDigit1)
            System.out.println("Match one digits: you win $1,000");
        else System.out.println("Sorry, no match");
    }
}
