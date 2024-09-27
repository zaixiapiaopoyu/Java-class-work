package org.example;

public class T_7_31 {
    public static int[] merge(int[] list1, int[] list2) {
        int[] result = new int[list1[0] + list2[0]];
        int k = 0,i ,j;
        for (i = 1,j = 1; i <= list1[0] && j <= list2[0];i++,j++) {
            if (list1[i] < list2[j]) {
                result[k] = list1[i];
                j--;
                k++;
            }
            else {
                result[k] = list2[j];
                i--;
                k++;
            }
        }
        while (i < list1[0]){
            result[k] = list1[i];
            i++;
        }
        while (j < list2[0]){
            result[k] = list2[j];
            j++;
        }
        return result;
    }
    public static void main(String[] args) {
        
    }
}
