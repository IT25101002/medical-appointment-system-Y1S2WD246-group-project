package Question_01;

public class StackArray {
    int maxSize;
    int[] stack;
    int top;

    // Constructor
    StackArray(int size) {
        maxSize = size;
        stack = new int[maxSize];
        top = -1;
    }

    // Push
    void push(int item) {
        if (isFull()) {
            System.out.println("Stack is full");
        } else {
            stack[++top] = item;
        }
    }

    // Pop
    int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        } else {
            return stack[top--];
        }
    }

    // Peek
    int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        } else {
            return stack[top];
        }
    }

    // Check empty
    boolean isEmpty() {
        return top == -1;
    }

    // Check full
    boolean isFull() {
        return top == maxSize - 1;
    }

    // Display
    void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            for (int i = top; i >= 0; i--) {
                System.out.print(stack[i] + " ");
            }
        }
        System.out.println();
    }
}
