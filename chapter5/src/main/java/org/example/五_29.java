package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 五_29 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> months = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December"));
        ArrayList<String> weeks = new ArrayList<String>(Arrays.asList("Sun", "Mon", "Tue", "Wed",
                "Thu", "Fri", "Sat"));
        List<Integer> days = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        System.out.print("Number of years: ");
        int year = input.nextInt();
        System.out.print("Number of day: ");
        int day = input.nextInt();
        if(year % 400 == 0 ||( year % 4 == 0 && year % 100 != 0)){
            days.set(1,29);
        }
        for (int i = 0; i < 12; i++) {
            System.out.printf("%37s", months.get(i) + ' ' + year);
            System.out.println();
            System.out.println("————————————————————————————————————————————————————————————————");
            for (int j = 0; j < 7; j++) {
                System.out.printf("%8s", weeks.get(j));
            }
            System.out.println();
            int d = 0;
            int k = 0;
            while (d < days.get(i)) {
                if (k < day) {
                    System.out.printf("%8s", " ");
                    k++;
                }
                else {
                    System.out.printf("%8d", (d+1));
                    d++;
                }
                if ((d+k)%7 == 0) {
                    System.out.println();
                }
            }
            day = (day + days.get(i))%7;
            System.out.println();
        }
    }
}
