class QueueArray {

    int maxSize;
    int[] queue;
    int front;
    int rear;
    int count;

    // Constructor
    QueueArray(int size) {
        maxSize = size;
        queue = new int[maxSize];
        front = 0;
        rear = -1;
        count = 0;
    }

    // Insert
    void insert(int item) {

        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }

        rear = (rear + 1) % maxSize; // circular move
        queue[rear] = item;
        count++;
    }

    // Remove
    int remove() {

        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }

        int temp = queue[front];
        front = (front + 1) % maxSize; // circular move
        count--;

        return temp;
    }

    // Peek Front
    int peekFront() {

        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }

        return queue[front];
    }

    // Check empty
    boolean isEmpty() {
        return count == 0;
    }

    // Check full
    boolean isFull() {
        return count == maxSize;
    }

    // Get number of elements
    int getCount() {
        return count;
    }

    // Display queue
    void display() {

        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        int i = front;

        for (int c = 0; c < count; c++) {
            System.out.print(queue[i] + " ");
            i = (i + 1) % maxSize;
        }

        System.out.println();
    }
}