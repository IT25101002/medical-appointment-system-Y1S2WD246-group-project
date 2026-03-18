package Q04;

import java.util.Scanner;

public class Question04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter total number of seconds: ");
        int totalSeconds = input.nextInt();

        int hours = totalSeconds / 3600;
        int remainingSeconds = totalSeconds % 3600;

        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;

        System.out.println("Time is:");
        System.out.println(hours + " hour(s), " + minutes + " minute(s), " + seconds + " second(s)");
    }
}
