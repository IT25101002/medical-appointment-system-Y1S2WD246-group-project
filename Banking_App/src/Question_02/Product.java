package Question_02;

public class Product {
    private int productId;
    private String productName;
    private double price;
    private int quantity;

    // Getter and Setter for productId
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    // Getter and Setter for productName
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter and Setter for price with validation
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price > 0) {
            this.price = price;
        } else {
            System.out.println("Price must be greater than 0");
        }
    }

    // Getter and Setter for quantity with validation
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Quantity cannot be negative");
        }
    }

    // Display product details
    public void displayDetails() {
        System.out.println("\nProduct Details");
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
    }

    // Calculate total stock value
    public double calculateTotalValue() {
        return price * quantity;
    }
}
