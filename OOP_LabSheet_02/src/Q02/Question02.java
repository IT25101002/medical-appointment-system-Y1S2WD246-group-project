package Q02;

import java.util.Scanner;

public class Question02 {
    public static void main(String[] args) {
        int[] numbers = new int[5];
        int largest_number = 0;
        int sum = 0;

        Scanner input = new Scanner(System.in);
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = input.nextInt();
            sum += numbers[i];

        }

        largest_number = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (largest_number < numbers[i]) {
                largest_number = numbers[i];
            } else if (largest_number == numbers[i]) {

            }else {

            }
        }

        System.out.println("The largest number is: " + largest_number);
    }
}
