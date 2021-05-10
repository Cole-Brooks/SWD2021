import java.util.LinkedList;
import java.util.Scanner;

public class Bank {
    /**
     * Main function to test or run the banking suite which I created for this project
     * @param args
     */
    public static void main(String args[]){
        LinkedList<Account> accountLinkedList = new LinkedList<Account>();
        System.out.println("Please select an option: \n1) Test Banking Suite\n2) Run Banking Program\n0) Exit");

        Scanner scnr = new Scanner(System.in); // create the scanner object
        int choice = -1;
        while(choice != 0 && choice != 1 && choice != 2){
            choice = scnr.nextInt();
            if(choice != 0 && choice != 1 && choice != 2){
                System.out.println("You've entered a non-valid input. Try again.");
            }
        }
        // Now we know choice = 0, 1, or 2
        switch(choice){
            case 0:
                System.out.println("Thank you!  Goodbye.");
                return;
            case 1:
                // Test the Banking Suite
                boolean testsPassed = true;
                int testNumber = 1;
                System.out.println("Testing The Banking Suite:");
                // TEST 1 - test that tests are being named properly
                Account testAccount = new Account("Test");
                if(!testAccount.getAccountHolder().equals("Test")){
                    testsPassed = false;
                    System.out.println("Test " + testNumber + " Failed");
                }
                testNumber ++;
                // TEST 2 - test that accounts are being numbered properly
                Account testAccount2 = new Account("Test2");
                if(testAccount.getAccountNumber() != 0 || testAccount2.getAccountNumber() != 1){
                    testsPassed = false;
                    System.out.println("Test " + testNumber + " Failed");
                    System.out.println("Expected account Numbers are 0 and 1... Actual values:");
                    System.out.println(testAccount.getAccountNumber() + " " + testAccount2.getAccountNumber());
                }
                testNumber ++;

                // TEST 3 - test that deposits are being made properly to all types of accounts
                CheckingAccount testCheck = new CheckingAccount("testCheck1", 100, 10);
                SavingsAccount testSave = new SavingsAccount("testSave", 10, 1000);
                LoanAccount testLoan = new LoanAccount("testLoan", 10, 10000);

                testCheck.deposit(100);
                testSave.deposit(50);
                testLoan.deposit(1000);

                if(testCheck.getBalance() != 200. || testSave.getBalance() != 1050. || testLoan.getBalance() != -9000.){
                    testsPassed = false;
                    System.out.println("Test " + testNumber + " Failed");
                    System.out.println("checking balance: 200, " + testCheck.getBalance());
                    System.out.println("saving balance: 1050, " + testSave.getBalance());
                    System.out.println("loan balance: -9000, " + testLoan.getBalance());
                }
                testNumber ++;
                // TEST 4 - test that withdrawals are being made properly from all types of accounts
                testCheck.withdrawal(100);
                testSave.withdrawal(50);
                testLoan.withdrawal(1000);

                if(testCheck.getBalance() != 100 || testSave.getBalance() != 1000 || testLoan.getBalance() != -9000.){
                    testsPassed = false;
                    System.out.println("Test " + testNumber + " Failed");
                    System.out.println("checking balance: 100, " + testCheck.getBalance());
                    System.out.println("saving balance: 1000, " + testSave.getBalance());
                    System.out.println("loan balance: -9000, " + testLoan.getBalance());
                }
                // TEST 5 - test that interest is being accrued properly on all types of accounts
                testAccount.compound();
                testSave.compound();
                testCheck.compound();
                testLoan.compound();

                if(testAccount.getBalance() != 0 || testCheck.getBalance() != 100. ||
                    testSave.getBalance() != 1100. || testLoan.getBalance() != -9900.){
                    testsPassed = false;
                    System.out.println("Test " + testNumber + " Failed");

                    System.out.println("checking balance: 100, " + testCheck.getBalance());
                    System.out.println("saving balance: 1100, " + testSave.getBalance());
                    System.out.println("loan balance: -9900, " + testLoan.getBalance());
                }

                // Tests are done, print results
                if(testsPassed){
                    System.out.println("All tests passed!");
                }
                else{
                    System.out.println("Some tests failed...");
                }
                break;
            case 2:
                // Run the banking program
                int runOpt = -1;
                while(runOpt != 0){
                    System.out.println("Welcome... Chose an option:");
                    System.out.println(
                            "1) Display All Accounts\n" +
                            "2) Create Account\n" +
                            "3) Deposit to Account\n" +
                            "4) Withdraw from Account\n" +
                                    "5) Accrue Account Interest\n" +
                            "0) Quit");
                    runOpt = scnr.nextInt();
                    // Run state machine
                    switch(runOpt){
                        case 0:
                            // do nothing
                            break;
                        case 1:
                            System.out.println("Displaying all accounts:");
                            System.out.println("Checking Accounts: \n");
                            CheckingAccount.printAccounts();
                            System.out.println("Savings Accounts: \n");
                            SavingsAccount.printAccounts();
                            System.out.println("Loan Accounts: \n");
                            LoanAccount.printAccounts();
                            // Organization newlines
                            System.out.println("\n");
                            break;
                        case 2:
                            System.out.println(
                                    "1) Checking Account\n" +
                                            "2) Savings Account\n" +
                                            "3) Loan Account\n");
                            int accountType = 0;
                            while(accountType != 1 && accountType != 2 && accountType != 3){
                                accountType = scnr.nextInt();
                            }
                            // initialize scanner for the names
                            switch(accountType){
                                case 1:
                                    // get params for constructor
                                    System.out.println("Input Info:");
                                    System.out.println("Account Holder First Name(string):");
                                    String name = scnr.next();
                                    System.out.println("Balance(double):");
                                    double balance = scnr.nextDouble();
                                    System.out.println("Overdraft Limit(double):");
                                    double overdraftLimit = scnr.nextDouble();
                                    System.out.println("Is this account for a corporation? Type 1 if yes, any other number if no");
                                    int isCorp = scnr.nextInt();
                                    accountLinkedList.add(new CheckingAccount(name, balance, overdraftLimit));
                                    if(isCorp == 1){
                                        accountLinkedList.get(accountLinkedList.size()-1).setCorporate(true);
                                    }
                                    break;
                                case 2:
                                    // get params for constructor
                                    System.out.println("Input Info:");
                                    System.out.println("Account Holder First Name(string):");
                                    String savingName = scnr.next();
                                    System.out.println("Balance(double):");
                                    double savingBalance = scnr.nextDouble();
                                    System.out.println("Interest Rate(double):");
                                    double interestRate = scnr.nextDouble();
                                    System.out.println("Is this account for a corporation? Type 1 if yes, any other number if no");
                                    int corpSavings = scnr.nextInt();
                                    accountLinkedList.add(new SavingsAccount(savingName, interestRate, savingBalance));
                                    break;
                                case 3:
                                    // get params for constructor
                                    System.out.println("Input Info:");
                                    System.out.println("Account Holder First Name(string):");
                                    String loanName = scnr.next();
                                    System.out.println("Balance(double):");
                                    double loanBalance = scnr.nextDouble();
                                    System.out.println("Interest Rate(double):");
                                    double loanRate = scnr.nextDouble();
                                    System.out.println("Is this loan for a corporation? Type 1 if yes, any other number if no");
                                    int corpLoan = scnr.nextInt();
                                    accountLinkedList.add(new LoanAccount(loanName, loanRate, loanBalance));
                                    if(corpLoan == 1){
                                        accountLinkedList.get(accountLinkedList.size()-1).setCorporate(true);
                                    }

                            }
                            break;
                        case 3:
                            // Deposit to an account:
                            System.out.println("Enter Account Number:");
                            int checkingNumber = scnr.nextInt();
                            if(checkingNumber >= accountLinkedList.size() || checkingNumber < 0){
                                System.out.println("No account found... Returning to home screen");
                            }
                            else{
                                System.out.println("Enter amount to deposit");
                                double checkingDeposit = scnr.nextDouble();
                                accountLinkedList.get(checkingNumber).deposit(checkingDeposit);
                            }
                            break;
                        case 4:
                            // Withdraw from an account:
                            System.out.println("Enter Account Number:");
                            int savingsNumber = scnr.nextInt();
                            if(savingsNumber >= accountLinkedList.size() || savingsNumber < 0){
                                System.out.println("No account found... Returning to home screen");
                            }
                            else{
                                System.out.println("Enter amount to withdraw");
                                double savingWithdrawal = scnr.nextDouble();
                                accountLinkedList.get(savingsNumber).withdrawal(savingWithdrawal);
                            }
                            break;
                        case 5:
                            // Accrue account interest
                            System.out.println("Enter Account Number:");
                            int accNum = scnr.nextInt();
                            if(accNum >= accountLinkedList.size() || accNum < 0){
                                System.out.println("No account found... Returning to home screen");
                            }
                            else{
                                try{
                                    accountLinkedList.get(accNum).compound();
                                }
                                catch(Exception e){
                                    System.out.println("Something went wrong!");
                                }
                            }
                            break;

                    }
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

}