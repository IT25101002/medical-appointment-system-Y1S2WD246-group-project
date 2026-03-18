package Question_03;

public class DriverApp {
    public static void main(String[] args) {

        Driver d1 = new Driver(101,"Ravindu");
        Driver d2 = new Driver(60000);
        Driver d3 = new Driver(102,"Kamal",70000,5);

        d3.displayDetails();

        System.out.println("Bonus using custom score: " + d3.calculateBonus());
    }

}
