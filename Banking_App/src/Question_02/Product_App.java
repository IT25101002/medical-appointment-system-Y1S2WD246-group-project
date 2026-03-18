import java.util.Scanner;

public class ProductApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Product p = new Product();

        System.out.print("Enter Product ID: ");
        p.setProductId(input.nextInt());
        input.nextLine();

        System.out.print("Enter Product Name: ");
        p.setProductName(input.nextLine());

        System.out.print("Enter Price: ");
        p.setPrice(input.nextDouble());

        System.out.print("Enter Quantity: ");
        p.setQuantity(input.nextInt());

        // Display product details
        p.displayDetails();

        // Display total stock value
        System.out.println("Total Stock Value: " + p.calculateTotalValue());
    }
}