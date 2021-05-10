import java.util.LinkedList;

public class SavingsAccount extends Account{
    /////////////////////////////////////////////////////////////////
    // FIELDS
    /////////////////////////////////////////////////////////////////

    /**
     * rate: interest rate for the savings account
     */
    private double rate;

    /**
     * savingsAccounts: a linked list to keep track of the number of savings accounts created
     */
    private static LinkedList<SavingsAccount> savingsAccounts = new LinkedList<SavingsAccount>();

    /////////////////////////////////////////////////////////////////
    // METHODS
    /////////////////////////////////////////////////////////////////

    // constructors

    /**
     * Constructor which takes only a name and starts balance at zero.
     * The default sets the interest rate to 1 percent
     *
     * @param name the name of the account holder
     */
    public SavingsAccount(String name) {
        super(name);
        savingsAccounts.add(this);
    }

    /**
     * Constructor that sets name and interest rate of the savings account by the parameters given and defaults the
     * balance of the account
     * @param name  name of the account holder
     * @param interestRate interest rate of the account
     */
    public SavingsAccount(String name, double interestRate){
        super(name);
        setRate(interestRate);
        savingsAccounts.add(this);
    }

    /**
     * Constructor that sets name, interest rate, and balance of the savings account
     * @param name  name of the account holder
     * @param interestRate interest rate of the account
     * @param balance initial balance of the account
     */
    public SavingsAccount(String name, double interestRate, double balance){
        super(name, balance);
        setRate(interestRate);
        savingsAccounts.add(this);
    }

    // Setters and getters

    /**
     * getRate
     * @return  rate at which the savings account accrues interest
     */
    public double getRate() {
        return rate;
    }

    /**
     * setRate
     * @param rate  rate at which the savings account accrues interest
     */
    public void setRate(double rate) {
        this.rate = rate;
        if(rate < 0){
            System.out.println("Savings Account Rates cannot be less than 0. Defaulting to 0");
            this.rate = 0;
        }
    }

    // Other methods

    /**
     * compound: compounds the interest in the savings account
     */
    public void compound(){
        this.setBalance(this.getBalance() * (1 + .01 * getRate()));
    }

    /**
     * toString: returns a string to be printed for the SavingsAccount class
     * @return the string to be printed
     */
    @Override
    public String toString(){
        StringBuilder retString = new StringBuilder("Account Holder: " + getAccountHolder() + "\nAccount Number: " + getAccountNumber() +
                "\nAccount Balance: " + getBalance());
        retString.append("\nInterest Rate: ");
        retString.append(getRate());
        if(isCorporate()){
            retString.append("\nAccount Type: Corporate Savings\n");
        }
        else{
            retString.append("\nAccount Type: Personal Savings\n");
        }
        return retString.toString();
    }
    /**
     * printAccounts: prints each account in the loanAccounts linked list
     */
    public static void printAccounts(){
        System.out.println(savingsAccounts);
    }

    /**
     * numCheckingAccounts:
     * @return  the integer size of the loanAccounts linked list which corresponds to number of active checking accounts
     */
    public static int numSavingsAccounts(){
        return savingsAccounts.size();
    }
}
