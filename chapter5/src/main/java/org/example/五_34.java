package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class 五_34 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        ArrayList <String> list = new ArrayList <String> (Arrays.asList("scissor" , "rock" , "paper"));
        int user = 0,computer = 0;
        while( (user != 3) && (computer != 3) ) {
            System.out.print("scissor (0), rock (1), paper (2): ");
            int userChoice = scanner.nextInt();
            int computerChoice = random.nextInt(3);
            if (userChoice == computerChoice) {
                System.out.println("The computer is " + list.get(computerChoice) + ". " + "You are " + list.get(userChoice) + " too. " + "It's a draw");
            }
            else if (((computerChoice == 0) && (userChoice == 1)) || ((computerChoice == 1) && (userChoice == 2)) || ((computerChoice == 2) && (userChoice == 0))) {
                System.out.println("The computer is " + list.get(computerChoice) + ". " + "You are " + list.get(userChoice) + ". " + "You won");
                user++;
            }
            else {
                System.out.println("The computer is " + list.get(computerChoice) + ". " + "You are " + list.get(userChoice) + ". " + "Computer won");
                computer++;
            }
        }
    }
}
