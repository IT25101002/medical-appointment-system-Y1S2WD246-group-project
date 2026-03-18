package Question_02;

public class ProductApp {
    public static void main(String[] args) {

        Product p1 = new Product(101,"Laptop");
        Product p2 = new Product(102,"Phone",100000);
        Product p3 = new Product(103,"Tablet",80000,"Premium");

        p1.displayProductDetails();
        System.out.println();

        p2.displayProductDetails();
        System.out.println();

        p3.displayProductDetails();

        System.out.println("Price with seasonal discount: " +
                p3.calculateFinalPrice(0.05));
    }
}

