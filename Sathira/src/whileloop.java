import java.util.Scanner;
public class whileloop {
    public static void main(String [] args){
    Scanner input = new Scanner (System.in);
    int total = 0;
    int kg = 0;
    int price = 0;

    System.out.print("enter the price of 1kg of rice");
    price = input.nextInt();

    System.out.print("enter the number of kilograms you want to buy");
    kg = input.nextInt();

    total = price * kg;

    System.out.print("The total amount is" + total);


    }
}