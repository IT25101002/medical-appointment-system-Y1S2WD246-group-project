package Question_03;

public class Driver {
    int driverID;
    String driverName;
    double baseSalary;
    int performanceScore;

    // Constructor 1
    public Driver(int driverID, String driverName) {
        this.driverID = driverID;
        this.driverName = driverName;
    }

    // Constructor 2
    public Driver(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    // Constructor 3
    public Driver(int driverID, String driverName,
                  double baseSalary, int performanceScore) {

        this.driverID = driverID;
        this.driverName = driverName;
        this.baseSalary = baseSalary;
        setPerformanceScore(performanceScore);
    }
    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public void setPerformanceScore(int performanceScore) {
        if(performanceScore >=1 && performanceScore <=5)
            this.performanceScore = performanceScore;
        else
            this.performanceScore = 1;
    }

    // Bonus calculation
    public double calculateBonus() {

        double rate = 0;

        switch(performanceScore) {
            case 5: rate = 0.25; break;
            case 4: rate = 0.20; break;
            case 3: rate = 0.15; break;
            case 2: rate = 0.10; break;
            case 1: rate = 0; break;
        }

        return baseSalary * rate;
    }
    public double calculateTotalSalary() {
        return baseSalary + calculateBonus();
    }

    public void displayDetails() {

        System.out.println("Driver ID: " + driverID);
        System.out.println("Driver Name: " + driverName);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Performance Score: " + performanceScore);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Total Salary: " + calculateTotalSalary());
    }

}
