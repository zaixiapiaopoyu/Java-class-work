package org.example;

import java.util.Scanner;

public class T_7_17 {
    public static void bubbleSort(int n,double[] scores,String[] names) {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n-i; j++) {
                if (scores[j-1] - scores[j] < 0) {
                    double temp = scores[j-1];
                    scores[j-1] = scores[j];
                    scores[j] = temp;
                    String tempName = names[j-1];
                    names[j-1] = names[j];
                    names[j] = tempName;
                }
            }
        }
    }
    public static void main(String[] args) {
            int n;
            String[] names = new String[1000];
            double[] scores = new double[1000];
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the numbers of the students: ");
        n = input.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the name of the student: ");
            names[i] = input.next();
            System.out.print("Enter the score of the student: ");
            scores[i] = input.nextDouble();
        }
        bubbleSort(n, scores, names);
        System.out.println("The names of the students are: ");
        for (int i = 0; i < n; i++) {
            System.out.print(scores[i] + " ");
            System.out.println(names[i]);
        }
    }
}
