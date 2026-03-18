package Q03;
import java.util.Scanner;
public class Question03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type your sentence: ");
        String sentence = sc.nextLine();

        String[] words = sentence.split(" ");
        System.out.println("Word count is: " + words.length);

        //for each loop-only to literate through arrays
        //for(data_type variable_name: array_name[])
        int maxlength = 0;
        for (String word : words) {
            if (word.length() >= maxlength)
                maxlength = word.length();
        }
        //print the longest word
        for(String word : words){
            if(word.length() == maxlength)
            System.out.println("Longest word is: " + word);
        }

        //revers sentence
        String reverrsesentence = new StringBuilder(sentence).reverse().toString();
        System.out.println("The sentence in reversed is: " + reverrsesentence);

    }
}
