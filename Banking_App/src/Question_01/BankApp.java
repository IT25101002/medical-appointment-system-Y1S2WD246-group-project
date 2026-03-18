package Question_01;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        String accNum = input.nextLine();

        System.out.print("Enter Account Name: ");
        String  accName = input.nextLine();

        Savings_Account acc_01 = new Savings_Account();

        acc_01.getAccount_name(accName);
        acc_01.getAccount_number(accNum);

        System.out.print("Enter initial deposit: ");
        double initial = input.nextDouble();
        acc_01.deposit(initial);

        System.out.print("Enter deposit amount: ");
        double dep = input.nextDouble();
        acc_01.deposit(dep);

        System.out.print("Enter withdraw amount: ");
        double wit = input.nextDouble();
        acc_01.withdraw(wit);

        acc_01.displayamountdetails();

    }
}