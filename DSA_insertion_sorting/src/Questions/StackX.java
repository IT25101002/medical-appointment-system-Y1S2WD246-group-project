package Questions;

public class StackX {
    private int maxSize;
    private int[] array;
    private int top;

    public StackX(int size){
        maxSize = size;
        array = new int[maxSize];
        top = -1;
    }
    public void push(int element){
        if (top == maxSize -1){
            System.out.println("Stack is Full");
        }
        else{
            array[++top] = element;
        }
    }
    public double pop(){
        if (top == -1){
            return 0.0;
        }
        else{
            return array[top--];
        }
    }
    public double peek(){
        if (top == -1){
            return 0.0;
        }
        else{
            return array[top];
        }
    }

    public boolean isEmpty() {
        if (top == -1){
            System.out.println("Stack is Empty");
            return true;
        }
        else{
            System.out.println("Stack is NOT Empty");
            return false;
        }
    }

}
