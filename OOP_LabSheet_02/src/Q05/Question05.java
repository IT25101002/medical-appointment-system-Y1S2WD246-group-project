package Q05;

import java.util.Scanner;

public class Question05 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String[] words = new String[5];

        System.out.println("Enter 5 words:");
        for (int i = 0; i < 5; i++) {
            words[i] = input.next();
        }

        String longestWord = words[0];
        for (int i = 1; i < 5; i++) {
            if (words[i].length() > longestWord.length()) {
                longestWord = words[i];
            }
        }

        int totalCharacters = 0;
        for (int i = 0; i < 5; i++) {
            totalCharacters += words[i].length();
        }

        System.out.println("\nWords with even number of characters:");
        for (int i = 0; i < 5; i++) {
            if (words[i].length() % 2 == 0) {
                System.out.println(words[i]);
            }
        }
        
        System.out.println("\nLongest word: " + longestWord);
        System.out.println("Total number of characters: " + totalCharacters);
    }
}
