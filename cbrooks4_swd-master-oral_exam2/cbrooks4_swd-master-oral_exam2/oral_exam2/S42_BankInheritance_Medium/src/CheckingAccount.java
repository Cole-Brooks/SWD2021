import java.util.LinkedList;

public class CheckingAccount extends Account{
    /////////////////////////////////////////////////////////////////
    // FIELDS
    /////////////////////////////////////////////////////////////////

    /**
     * overdraftLimit: The maximum amount that an account can go below zero and still complete a transaction
     */
    private double overdraftLimit;
    /**
     * checkingAccounts: a linked list of all checking accounts currently active
     */
    private static LinkedList<CheckingAccount> checkingAccounts = new LinkedList<CheckingAccount>();

    /////////////////////////////////////////////////////////////////
    // METHODS
    /////////////////////////////////////////////////////////////////

    // constructors

    /**
     * Constructor which takes only a name and starts balance at zero. Overdraft limit defaults to 10 dollars
     *
     * @param name the name of the account holder
     */
    public CheckingAccount(String name) {
        super(name);
        setOverdraftLimit(10);
        checkingAccounts.add(this);
    }

    /**
     * Constructor which takes both a name and a starting balance. Overdraft limit defaults to 10 dollars
     * @param name the name of the account holder
     * @param balance the starting balance of the account
     */
    public CheckingAccount(String name, double balance){
        super(name, balance);
        setOverdraftLimit(10);
        checkingAccounts.add(this);
    }

    /**
     * Constructor which takes a name, starting balance and an overdraft limit for the account
     * @param name  name of the account holder
     * @param balance initial balance of the account
     * @param overdraftLimit overdraft limit of the account
     */
    public CheckingAccount(String name, double balance, double overdraftLimit){
        super(name, balance);
        setOverdraftLimit(overdraftLimit);
        checkingAccounts.add(this);
    }

    // setters and getters

    /**
     * getOverdraftLimit
     * @return  double value of the overdraft limit
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * setOverdraftLimit
     * @param overdraftLimit set the overdraft limit of the account to this double
     */
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    // Other methods
    @Override
    public void withdrawal(double amount){
        double newBalance = getBalance() - amount;
        if(newBalance < -1 * overdraftLimit){
            System.out.println("Insufficient Funds to complete this transaction");
        }
        else{
            setBalance(newBalance);
        }
    }

    /**
     * printAccounts: prints each account in the checkingAccounts linked list
     */
    public static void printAccounts(){
        System.out.println(checkingAccounts);
    }

    /**
     * numCheckingAccounts:
     * @return  the integer size of the checkingAccounts linked list which corresponds to number of active checking accounts
     */
    public static int numCheckingAccounts(){
        return checkingAccounts.size();
    }
    @Override
    public String toString(){
        StringBuilder retString = new StringBuilder("Account Holder: " + getAccountHolder() + "\nAccount Number: " + getAccountNumber() +
                "\nAccount Balance: " + getBalance());
        if(isCorporate()){
            retString.append("\nAccount Type: Corporate Checking\n");
        }
        else{
            retString.append("\nAccount Type: Personal Checking\n");
        }
        return retString.toString();
    }
}
