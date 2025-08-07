import java.util.Scanner;

class BankAccount{
    private int accountNumber;
    private String accountHolder;
    private double Balance;

    BankAccount(int accountNumber, String accountHolder, double Balance){
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.Balance = Balance;
    }

    public double getBalance(){
        return Balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            Balance += amount;
            System.out.println("Deposited $" + amount + " into account #" + accountNumber);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && Balance >= amount) {
            Balance -= amount;
            System.out.println("Withdrawn $" + amount + " from account #" + accountNumber);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }

    public String toString(){
        return "Account: " + accountNumber + "\n" + "Your Current Balance is: " + Balance;
    }
}

class SavingsAccount extends BankAccount {

    SavingsAccount(int accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount >= 1000) {
            super.withdraw(amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    @Override
    public String toString(){
        return "Savings: " + super.toString();
    }
}

class CheckingAccount extends BankAccount {
    public CheckingAccount(int accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public String toString() {
        return "Checking " + super.toString();
    }
}

class PPS4_3{
    public static void main(String...Args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter account number: ");
        int num = input.nextInt();
        input.nextLine();
        System.out.print("Enter holder name: ");
        String name = input.nextLine();
        System.out.print("Enter Balance: ");
        double bal = input.nextDouble();
        System.out.println("1. Savings Account\n2. Checking Account");
        System.out.println("Select account type: ");
        int type = input.nextInt();
        if (type == 1){
            SavingsAccount SA = new SavingsAccount(num, name, bal);
            System.out.println("1. Check info \n2. Withdraw \n3. Exit");
            System.out.println("Enter choice: ");
            int choice = input.nextInt();
            while (true){
                if (choice == 1){ System.out.println(SA);}
                else if (choice == 2){
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = input.nextDouble();
                    SA.withdraw(withdraw);
                } else if (choice == 3){ break; } 
            }
        } else if (type == 2){
            CheckingAccount CA = new CheckingAccount(num, name, bal);
        }
    }
}