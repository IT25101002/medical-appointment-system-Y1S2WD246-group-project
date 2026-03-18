package Question_02;

public class Product {
    int productID;
    String productName;
    double basePrice;
    String membershipType;

    // Constructor 1
    public Product(int productID, String productName) {
        this.productID = productID;
        this.productName = productName;
        this.membershipType = "Non-Member";
    }

    // Constructor 2
    public Product(int productID, String productName, double basePrice) {
        this(productID, productName);
        this.basePrice = basePrice;
    }

    // Constructor 3
    public Product(int productID, String productName, double basePrice, String membershipType) {
        this.productID = productID;
        this.productName = productName;
        this.basePrice = basePrice;
        setMembershipType(membershipType);
    }
    // Setter with validation
    public void setMembershipType(String membershipType) {
        if(membershipType.equalsIgnoreCase("Premium") ||
                membershipType.equalsIgnoreCase("Regular") ||
                membershipType.equalsIgnoreCase("Non-Member"))
        {
            this.membershipType = membershipType;
        }
        else {
            this.membershipType = "Non-Member";
        }
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    // Method 1
    public double calculateFinalPrice() {

        double discount = 0;

        if(membershipType.equalsIgnoreCase("Premium"))
            discount = 0.20;
        else if(membershipType.equalsIgnoreCase("Regular"))
            discount = 0.10;

        return basePrice - (basePrice * discount);
    }

    // Method 2 (seasonal discount)
    public double calculateFinalPrice(double seasonalDiscount) {
        double price = calculateFinalPrice();
        return price - (price * seasonalDiscount);
    }

    public void displayProductDetails() {

        System.out.println("Product ID: " + productID);
        System.out.println("Product Name: " + productName);
        System.out.println("Base Price: " + basePrice);
        System.out.println("Membership: " + membershipType);
        System.out.println("Final Price: " + calculateFinalPrice());
    }

}
