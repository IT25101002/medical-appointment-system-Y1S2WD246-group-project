package Question_03;

import java.util.Scanner;

public class Employee {
    // Private instance variables for encapsulation
    private int employeeId;
    private String employeeName;
    private double basicSalary;
    private int performanceRating;
    private double bonus;
    private double totalSalary;
    
    // Constructor
    public Employee(int employeeId, String employeeName, double basicSalary, int performanceRating) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.basicSalary = basicSalary;
        setPerformanceRating(performanceRating); // Using setter to validate
    }
    
    // Getter and Setter methods for encapsulation
    public int getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public double getBasicSalary() {
        return basicSalary;
    }
    
    public void setBasicSalary(double basicSalary) {
        if (basicSalary > 0) {
            this.basicSalary = basicSalary;
        } else {
            System.out.println("Basic salary must be positive!");
        }
    }
    
    public int getPerformanceRating() {
        return performanceRating;
    }
    
    public void setPerformanceRating(int performanceRating) {
        // Validate performance rating (1-5)
        if (performanceRating >= 1 && performanceRating <= 5) {
            this.performanceRating = performanceRating;
        } else {
            System.out.println("Invalid performance rating! Setting default rating of 1.");
            this.performanceRating = 1; // Default to lowest rating
        }
    }
    
    // Method to calculate bonus based on performance rating
    public void calculateBonus() {
        switch (performanceRating) {
            case 5:
                bonus = basicSalary * 0.20; // 20% bonus
                break;
            case 4:
                bonus = basicSalary * 0.15; // 15% bonus
                break;
            case 3:
                bonus = basicSalary * 0.10; // 10% bonus
                break;
            case 2:
                bonus = basicSalary * 0.05; // 5% bonus
                break;
            case 1:
                bonus = 0.0; // No bonus
                break;
            default:
                bonus = 0.0;
                System.out.println("Invalid rating for bonus calculation!");
        }
    }
    
    // Method to calculate total salary (Basic Salary + Bonus)
    public void calculateTotalSalary() {
        totalSalary = basicSalary + bonus;
    }
    
    // Method to display employee details
    public void displayEmployeeDetails() {
        System.out.println("\nEmployee Details:");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + employeeName);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Performance Rating: " + performanceRating);
        System.out.println("Bonus: " + bonus);
        System.out.println("Total Salary: " + totalSalary);
    }
    
    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Get employee details from user
            System.out.print("Enter Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Basic Salary: ");
            double salary = scanner.nextDouble();
            
            System.out.print("Enter Performance Rating (1-5): ");
            int rating = scanner.nextInt();
            
            // Create Employee object
            Employee employee = new Employee(id, name, salary, rating);
            
            // Calculate bonus and total salary
            employee.calculateBonus();
            employee.calculateTotalSalary();
            
            // Display employee details
            employee.displayEmployeeDetails();
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input! Please enter valid values.");
        } finally {
            scanner.close();
        }
    }
}