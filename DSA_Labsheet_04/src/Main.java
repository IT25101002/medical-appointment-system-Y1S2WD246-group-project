import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter queue size: ");
        int size = input.nextInt();

        QueueArray q = new QueueArray(size);

        // Fill queue
        System.out.println("Insert " + size + " elements:");

        for (int i = 0; i < size; i++) {
            int value = input.nextInt();
            q.insert(value);
        }

        System.out.println("Queue elements:");
        q.display();

        // Remove two elements
        System.out.println("Removed: " + q.remove());
        System.out.println("Removed: " + q.remove());

        System.out.println("Queue after removal:");
        q.display();

        // Insert again to show circular behavior
        System.out.println("Insert 2 more elements:");
        q.insert(input.nextInt());
        q.insert(input.nextInt());

        System.out.println("Queue after circular insert:");
        q.display();

        System.out.println("Front element: " + q.peekFront());
        System.out.println("Number of elements: " + q.getCount());
    }
}