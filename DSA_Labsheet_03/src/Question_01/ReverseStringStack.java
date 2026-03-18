package Question_01;

import java.util.Scanner;

class CharStack {
    int maxSize;
    char[] stack;
    int top;

    CharStack(int size) {
        maxSize = size;
        stack = new char[maxSize];
        top = -1;
    }

    void push(char c) {
        stack[++top] = c;
    }

    char pop() {
        return stack[top--];
    }

    boolean isEmpty() {
        return top == -1;
    }
}

public class ReverseStringStack {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String word = input.nextLine();

        CharStack stack = new CharStack(word.length());

        // Push characters
        for (int i = 0; i < word.length(); i++) {
            stack.push(word.charAt(i));
        }

        // Pop characters to reverse
        System.out.print("Reversed string: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}