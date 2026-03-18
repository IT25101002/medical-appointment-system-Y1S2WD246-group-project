package Question01;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise03 {
    public static void main(String[] args) {
        int size = 0;
        int i,key;
        int j;
        int count = 0;

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        size = input.nextInt();

        int[] Array = new int[size];

        for (i = 0; i < size; i++){
            System.out.print("Enter element " + (i+1) + ": ");
            Array[i] = input.nextInt();
        }

        for (i = 1; i < Array.length; i++){
            key = Array[i];
            j = i - 1;

            while ( j >= 0 && Array[j] > key ){
                Array[j + 1] = Array[j];
                j--;
            }
            Array[j+1] = key;
            System.out.println("Array list is: " + Arrays.toString(Array));;
        }

        System.out.print("Array in assending order is: ");
        for (i = 0; i < Array.length; i++){
            System.out.print(Array[i] + " ");
        }
    }
}
