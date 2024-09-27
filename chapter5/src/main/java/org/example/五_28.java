package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class 五_28 {
    public static void main(String[] args) {
        ArrayList <String> months = new ArrayList<String>(Arrays.asList("January" , "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November" , "December"));
        ArrayList <String> weeks = new ArrayList<String>(Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"));
        List<Integer> days = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        Scanner input = new Scanner(System.in);
        System.out.print("Number of years: ");
        int years = input.nextInt();
        System.out.print("Number of days: ");
        int day = input.nextInt();
        for (int i = 0; i < 12; i++) {
            if((years % 400 == 0 ||( years % 4 == 0 && years % 100 != 0)) && i == 2){
                day += 1;
            }
            System.out.print(months.get(i) + " " + "1, " + years + " is ");
            System.out.println(weeks.get(day));
            day = (day + days.get(i))%7;
        }
    }
}
