package Question_03;

public class Employee {
    private int employeeId;
    private String employeeName;
    private double basicSalary;
    private int performanceRating;

    // Getter and Setter for employeeId
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    // Getter and Setter for employeeName
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    // Getter and Setter for basicSalary
    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        if(basicSalary > 0){
            this.basicSalary = basicSalary;
        } else {
            System.out.println("Salary must be greater than 0");
        }
    }

    // Getter and Setter for performanceRating
    public int getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(int performanceRating) {
        if(performanceRating >= 1 && performanceRating <= 5){
            this.performanceRating = performanceRating;
        } else {
            System.out.println("Rating must be between 1 and 5");
        }
    }

    // Method to calculate bonus
    public double calculateBonus() {

        double bonusRate = 0;

        switch(performanceRating){
            case 5: bonusRate = 0.20; break;
            case 4: bonusRate = 0.15; break;
            case 3: bonusRate = 0.10; break;
            case 2: bonusRate = 0.05; break;
            case 1: bonusRate = 0.0; break;
        }

        return basicSalary * bonusRate;
    }

    // Method to calculate final salary
    public double calculateFinalSalary(){
        return basicSalary + calculateBonus();
    }

    // Display employee details
    public void displayDetails(){
        System.out.println("\nEmployee Details");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Performance Rating: " + performanceRating);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Final Salary: " + calculateFinalSalary());
    }
}
