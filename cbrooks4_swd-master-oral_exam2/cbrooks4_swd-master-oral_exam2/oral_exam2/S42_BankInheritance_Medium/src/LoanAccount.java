import java.util.LinkedList;

public class LoanAccount extends SavingsAccount{
    /////////////////////////////////////////////////////////////////
    // FIELDS
    /////////////////////////////////////////////////////////////////
    private static LinkedList<LoanAccount> loanAccounts = new LinkedList<LoanAccount>();

    /////////////////////////////////////////////////////////////////
    // METHODS
    /////////////////////////////////////////////////////////////////

    /**
     * Constructor for loan account. Note that loan accounts require a name, an interest rate, and a balance
     *
     * @param name the name of the account holder
     * @param interestRate  Rate at which interest is accrued
     * @param balance   Initial balance on the account
     */
    public LoanAccount(String name, double interestRate, double balance) {
        // Note that balance is still input as positive, but will be stored as negative
        super(name, interestRate, -1 * balance);
        loanAccounts.add(this);
    }

    /**
     * Constructor for loan account which takes a name, an interest rate, a balance, and an isCorporate boolean
     * @param name  The name of the account holder
     * @param interestRate Rate at which interest is accrued
     * @param balance Initial balance on the account
     * @param isCorporate boolean referring to whether the accountHolder is a corporation (true) or an individual (false)
     */
    public LoanAccount(String name, double interestRate, double balance, boolean isCorporate){
        super(name, interestRate, -1 * balance);
        setCorporate(isCorporate);
    }

    /**
     * printLoanAccounts: prints each account in the loanAccounts linked list
     */
    public static void printAccounts(){
        System.out.println(loanAccounts);
    }

    /**
     * numCheckingAccounts:
     * @return  the integer size of the loanAccounts linked list which corresponds to number of active checking accounts
     */
    public static int numLoanAccounts(){
        return loanAccounts.size();
    }

    // Override methods that don't make sense for loan accounts
    @Override
    public void withdrawal(double amount){
        System.out.println("Withdrawals may not be made from a loan account");
    }
}
