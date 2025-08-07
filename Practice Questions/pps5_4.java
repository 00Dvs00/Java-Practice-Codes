import java.util.Scanner;

class InsufficientFundsException extends Exception{
    public InsufficientFundsException (String exception){
        super(exception);
    }
}

class BankAccount{
    int accountNumber;
    String accountHolderName;
    double balance;

    BankAccount(int accountNumber, String accountHolderName, double balance){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    void withdraw(double withdraw) throws InsufficientFundsException {
        if (withdraw>balance){
            throw new InsufficientFundsException("You dont have Enough balance in your bank to withdraw the amount!");
        }
        else{
            balance = balance - withdraw;
            System.out.println("\nWithdrawal Succesful!");
            System.out.println("Current Balance: " + balance);
        }
    }
}

class pps5_4{
    public static void main(String...args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        int accountNumber = input.nextInt();
        System.out.print("Enter Your Name: ");
        String accountHolderName = input.nextLine();
        input.nextLine();
        System.out.print("Enter the Balance in account: ");
        double balance = input.nextDouble();

        BankAccount account = new BankAccount(accountNumber, accountHolderName, balance);

        System.out.print("Enter amount to Withdraw: ");
        double withdraw = input.nextDouble();
        
        try{
            account.withdraw(withdraw);
        }
        catch(InsufficientFundsException fund){
            System.out.println("\nWe Have caught an Exception!!");
            System.out.println("Exception detail \n" + fund + "\n");
        }
        input.close();
    }
}