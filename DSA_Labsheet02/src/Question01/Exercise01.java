package Question01;

public class Exercise01 {
    public static void main(String[] args) {
        int[] Array = {6,4,3,1,2,5};
        int i,key;
        int j;
        int count = 0;

        for (i = 1; i < Array.length; i++){
            key = Array[i];
            j = i - 1;

            while ( j >= 0 && Array[j] > key ){
                Array[j + 1] = Array[j];
                j--;
            }
            Array[j+1] = key;
        }

        System.out.print("Array in assending order is: ");
        for (i = 0; i < Array.length; i++){
            System.out.print(Array[i] + " ");
        }
        System.out.print("Number of counts: " + count);
    }
}