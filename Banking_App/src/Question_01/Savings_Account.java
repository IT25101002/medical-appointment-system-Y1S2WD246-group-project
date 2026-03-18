package Question_01;

public class Savings_Account{
    private String Account_name;
    private String Account_number;
    private double balance;

    public String getAccount_number(String accNum){
        return Account_number;
    }
    public String getAccount_name(String accName){
        return Account_name;
    }
    public double getBalance(){
        return balance;
    }

    public void setAccount_number(String accNumber){
        this.Account_number = accNumber;
    }
    public void setAccount_name(String acc_Name){
        this.Account_name = acc_Name;
    }

    public void deposit(double amount){
        if (amount > 0){
            balance += amount;
            System.out.print("Deposit amount: " + amount + "\n new balance: " + balance + "\n");
        }
        else {
            System.out.println("Amount cannot be negative");
        }
    }

    public void withdraw(double amount){
        if (balance > 1000 && balance > amount){
            balance -= amount;
            System.out.print("Withdrawal amount: " + amount + "New balance: " + balance + "\n");
        }
        else {
            System.out.print("Insufficient funds\n");
        }
    }
    public void displayamountdetails(){
        System.out.println("Account details\n");
        System.out.println("Account Name: " + Account_name);
        System.out.println("Account Number: " + Account_number);
        System.out.println("Balance: " + balance);
    }

}
