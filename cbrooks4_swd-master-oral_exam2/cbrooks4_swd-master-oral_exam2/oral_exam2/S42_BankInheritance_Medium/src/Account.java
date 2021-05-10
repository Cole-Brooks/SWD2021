import java.util.LinkedList;

public class Account{
    /////////////////////////////////////////////////////////////////
    // FIELDS
    /////////////////////////////////////////////////////////////////
    private static LinkedList<Account> accountNumbers = new LinkedList<Account>();
    /**
     * accountHolder: Name of the person/company who owns the account
     */
    private String accountHolder;
    /**
     * isCorporate: boolean value to denote whether the account is owned by an individual
     * or a corporation/business. True denotes corporation, false denotes individual. Note that
     * the default is always false.
     */
    private boolean isCorporate = false;
    /**
     * accountNumber: unique identifying number for the account.  Currently not supported, but would be nice
     * to add
     */
    private int accountNumber;
    /**
     * balance: the balance on the account
     */
    private double balance;

    /////////////////////////////////////////////////////////////////
    // METHODS
    /////////////////////////////////////////////////////////////////

    // Constructors
    /**
     * Constructor which takes only a name and starts balance at zero
     * @param name  the name of the account holder
     */
    public Account(String name){
        setAccountHolder(name);
        setAccountNumber();
        setBalance(0);
    }

    /**
     * Constructor which takes the name to set the account holder field, and an initial balance for the account
     * @param name
     * @param balance
     */
    public Account(String name, double balance){
        setAccountHolder(name);
        setAccountNumber();
        setBalance(balance);
    }

    // Getters and Setters
    /**
     * setAccountHolder
     * @param accountHolder String value to set accountHolder's name to for bank records
     */
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    /**
     * getAccountHolder
     * @return  String name of the account holder
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    /**
     * isCorporate
     * @return boolean value of isCorporate.  True if corporate account, else false
     */
    public boolean isCorporate() {
        return isCorporate;
    }

    /**
     * setCorporate
     * @param corporate sets the boolean value of isCorporate. True if corporate account, else false
     */
    public void setCorporate(boolean corporate) {
        isCorporate = corporate;
    }

    /**
     * getBalance:
     * @return  balance of the account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * setBalance:
     * @param balance new balance of the account.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * setAccountNumber:
     * a private function only called by the constructor of an account so that account numbers will be given out in order.
     */
    private void setAccountNumber(){
        accountNumber = accountNumbers.size();
        accountNumbers.add(this);
    }

    /**
     * getAccountNumber
     * @return  integer account number
     */
    public int getAccountNumber(){
        return accountNumber;
    }

    /**
     * getAccountList
     * @return  All accounts currently instantiated in a linked list
     */
    public LinkedList<Account> getAccountList(){
        return accountNumbers;
    }
    // Other Methods

    /**
     * withdrawal
     * @param amount amount to withdraw from the account
     */
    public void withdrawal(double amount){
        double newBalance = getBalance() - amount;
        if(newBalance < 0){
            System.out.println("Insufficient Funds available for this transaction");
        }
        else{
            setBalance(newBalance);
        }
    }

    /**
     * deposit
     * @param amount amount to be deposited into the account
     */
    public void deposit(double amount){
        setBalance(getBalance() + amount);
    }

    /**
     * compound does nothing for Account superclass
     */
    public void compound(){

    }

    @Override
    public String toString(){
        return("Account Holder: " + getAccountHolder() + "\nAccount Number: " + getAccountNumber() +
                "Account Balance: " + getBalance());
    }
}