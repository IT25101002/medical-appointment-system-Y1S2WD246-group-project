package Questions;

import java.util.Stack;

public class StackApp {
    public static void main(String[] args) {
        StackX thestack = new StackX(5);

        thestack.push(400);
        thestack.push(500);
        thestack.push(40);
        thestack.push(70);

        thestack.pop();

        System.out.println("What is in the  top: " + thestack.peek());

        thestack.pop();

        System.out.println("What is in the top: " + thestack.peek());

        thestack.isEmpty();
    }
}