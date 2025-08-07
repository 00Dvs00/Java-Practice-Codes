import java.util.Scanner;

interface Transaction{
        void deposit(double amount);
        void withdraw(double amount);
}

class BankAccount{
    private int accountNumber;
    private String accountHolder;
    protected double Balance;
    private String accountType;

    public double getBalance(){
        return Balance;
    }

    public BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.Balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            Balance += amount;
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && Balance >= amount) {
            Balance -= amount;
            System.out.println("Withdrawn $" + amount);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }

    public String displayAccountInfo(){
        return "Your Current Balance is: " + Balance;
    }
}

class SavingsAccount extends BankAccount implements Transaction{

    SavingsAccount(int accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            Balance += amount;
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && Balance >= amount && (Balance-amount)>=1000) {
            Balance -= amount;
            System.out.println("Withdrawn $" + amount);
        } else {
            System.out.println("Insufficient funds or Minimum Balance ($1000) limit is not satisfied. \n");
        }
    }

    @Override
    public String displayAccountInfo(){
        return "Savings: " + super.displayAccountInfo();
    }
}

class CheckingAccount extends BankAccount implements Transaction{
    public CheckingAccount(int accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }
    
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            Balance += amount;
            System.out.println("Deposited $" + amount + "\n");
        } else {
            System.out.println("Invalid deposit amount. \n");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && Balance >= amount) {
            Balance -= amount;
            System.out.println("Withdrawn $" + amount + "\n");
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount. \n");
        }
    }

    
    @Override
    public String displayAccountInfo() {
        return "Checking " + super.displayAccountInfo();
    }
}

class PPS4_5{ 
    public static void main(String...Args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter account number: ");
        int num = input.nextInt();
        input.nextLine();
        System.out.print("Enter holder name: ");
        String name = input.nextLine();
        System.out.println("1. Savings Account\n2. Checking Account");
        System.out.print("Select account type: ");
        int type = input.nextInt();
        System.out.print("Enter Balance: ");
        double bal = input.nextDouble();
        while (true){
            if (type == 1){
                SavingsAccount SA = new SavingsAccount(num, name, bal);
                System.out.println("1. Deposit \n2. Withdraw \n3. Exit");
                System.out.print("Enter choice: ");
                int choice = input.nextInt();
                if (choice == 1){ 
                    System.out.print("Enter amount to Deposit: ");
                    double deposit = input.nextDouble();
                    SA.deposit(deposit);
                    System.out.println(SA.displayAccountInfo());
                }
                else if (choice == 2){
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = input.nextDouble();
                    SA.withdraw(withdraw);
                    System.out.println(SA.displayAccountInfo());
                } else if (choice == 3){ break; }
            } else if (type == 2){
                CheckingAccount CA = new CheckingAccount(num, name, bal);
                System.out.println("1. Deposit \n2. Withdraw \n3. Exit");
                System.out.print("Enter choice: ");
                int choice = input.nextInt();
                if (choice == 1){ 
                    System.out.print("Enter amount to Deposit: ");
                    double deposit = input.nextDouble();
                    CA.deposit(deposit);
                    System.out.println(CA.displayAccountInfo());
                    break;
                }
                else if (choice == 2){
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = input.nextDouble();
                    CA.withdraw(withdraw);
                    System.out.println(CA.displayAccountInfo());
                    break;
                } else if (choice == 3){ break; } 
            }
        }      
    }
}