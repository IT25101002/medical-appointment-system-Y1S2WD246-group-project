package Question_03;
import java.util.Scanner;
public class EmployeeApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Employee emp = new Employee();

        System.out.print("Enter Employee ID: ");
        emp.setEmployeeId(input.nextInt());
        input.nextLine();

        System.out.print("Enter Employee Name: ");
        emp.setEmployeeName(input.nextLine());

        System.out.print("Enter Basic Salary: ");
        emp.setBasicSalary(input.nextDouble());

        System.out.print("Enter Performance Rating (1-5): ");
        emp.setPerformanceRating(input.nextInt());

        emp.displayDetails();

    }
}
