package org.example;

import java.util.Scanner;

public class ComputerAreaWithConsoleInput22 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number of radius: ");
        double radius = input.nextDouble();
        double are = radius * radius * 3.14159;
        System.out.println("The area for the circle of radius: " + radius + " is " + are);
    }
}
