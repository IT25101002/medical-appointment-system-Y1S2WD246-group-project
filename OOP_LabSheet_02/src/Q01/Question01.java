package Q01;

import java.util.Scanner;
public class Question01 {
    public static void main(String[] args) {
        int em_type;
        int basic_salary;
        int overtime_hours;
        int otRate = 0;
        int total_salary;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter employee type(as 1,2,3): ");
        em_type = input.nextInt();

        System.out.println("Enter your basic salary: ");
        basic_salary = input.nextInt();

        System.out.println("Enter your overtime hours: ");
        overtime_hours = input.nextInt();

        if (em_type == 1) {
            otRate = 1000;
        }
        else if (em_type == 2) {
            otRate = 1500;
        }
        else if (em_type == 3) {
            otRate = 1700;
        }
        else {

            System.out.println("Invalid input");
        }

        total_salary = basic_salary + (overtime_hours * otRate);

        System.out.println("Your total salary is: " + total_salary);
    }
}