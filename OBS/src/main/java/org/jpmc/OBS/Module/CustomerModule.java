package org.jpmc.OBS.Module;

import org.jpmc.OBS.dao.customerDAO;
import org.jpmc.OBS.entity.Customer;
//import org.hibernate.mapping.List;
import org.jpmc.OBS.dao.AccountDAO; 
import org.jpmc.OBS.dao.LoanDAO; 
import org.jpmc.OBS.dao.TransactionDao; 
import org.jpmc.OBS.entity.Account; 
import org.jpmc.OBS.entity.Loan; 
import org.jpmc.OBS.entity.Transaction;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;


public class CustomerModule {
	
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	 public static final String ANSI_CYAN = "\u001B[36m";
	

    private customerDAO customerDAO;

    // Constructor for dependency injection
    public CustomerModule() {
        this.customerDAO = new customerDAO(); // Initialize the DAO properly
    }

    public void customerMenu(String customerUsername) {
        // Welcome message for the customer
    	System.out.print(ANSI_RED);
        System.out.println("Welcome, Customer " + customerUsername);
        // Display the main customer management menu
        displayCustomerMenu();
    }

    // Display the main customer management menu
    private void displayCustomerMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
        	System.out.print(ANSI_PURPLE);
            System.out.println("\n*** Customer Menu ***");
            System.out.print(ANSI_BLUE);
            System.out.println("1. Customer Details");
            System.out.println("2. Account Details");
            System.out.println("3. Loan Management");
            System.out.println("4. Transaction Management");
            System.out.println("5. Logout");
            System.out.print(ANSI_PURPLE);
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    manageCustomerDetails();
                    break;
                case 2:
                    manageAccountDetails();
                    break;
                case 3:
                    manageLoanDetails();
                    break;
                case 4:
                    manageTransactionDetails();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 5);
    }

    // Customer Details Submenu
    private void manageCustomerDetails() {
        Scanner sc = new Scanner(System.in);
        int option;
        do {
        	System.out.print(ANSI_GREEN);
            System.out.println("\n*** Customer Details Menu ***");
            System.out.print(ANSI_BLUE);
            System.out.println("1. View Personal Info");
            System.out.println("2. Update Details");
            System.out.println("3. Return to Main Menu");
            System.out.print(ANSI_PURPLE);
            System.out.print("Enter your choice: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    viewPersonalInfo();
                    break;
                case 2:
                    updateCustomer();
                    break;
                
                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (option != 3);
    }

    // View Personal Info
    private void viewPersonalInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print(ANSI_GREEN);
        System.out.print("Enter Customer ID to view: ");
        String customerId = sc.nextLine();

        Customer customer = customerDAO.getCustomerById(customerId);

        if (customer != null) {
        	System.out.print(ANSI_PURPLE);
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Phone: " + customer.getPhoneNumber());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("DOB: " + new SimpleDateFormat("dd/MM/yyyy").format(customer.getDob()));
            System.out.println("Username: " + customer.getName());
        } else {
        	System.out.print(ANSI_RED);
            System.out.println("Customer with ID " + customerId + " not found!");
        }
    }

    // Update Customer Details
    private void updateCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print(ANSI_BLUE);
        System.out.print("Enter Customer ID to update: ");
        String customerId = sc.nextLine();

        Customer customer = customerDAO.getCustomerById(customerId);

        if (customer != null) {
            System.out.print("Enter new Name (current: " + customer.getName() + "): ");
            String name = sc.nextLine();
            System.out.print("Enter new Phone Number (current: " + customer.getPhoneNumber() + "): ");
            String phoneNumber = sc.nextLine();
            System.out.print("Enter new Address (current: " + customer.getAddress() + "): ");
            String address = sc.nextLine();
            System.out.print("Enter new Date of Birth (dd/MM/yyyy, current: " + new SimpleDateFormat("dd/MM/yyyy").format(customer.getDob()) + "): ");
            String dobStr = sc.nextLine();
            Date dob = parseDate(dobStr);

            if (dob != null) {
                customer.setName(name);
                customer.setPhoneNumber(phoneNumber);
                customer.setAddress(address);
                customer.setDob(dob);

                customerDAO.updateCustomer(customer); 
                System.out.print(ANSI_PURPLE);
                System.out.println("Customer details updated successfully!");
            }
        } else {
        	System.out.print(ANSI_RED);
            System.out.println("Customer not found!");
        }
    }

    // Account Details Submenu
    private void manageAccountDetails() {
        Scanner sc = new Scanner(System.in);
        AccountDAO accountDAO = new AccountDAO();
        int option;
        do {
            System.out.print(ANSI_GREEN);
            System.out.println("\n*** Account Details Menu ***");
            System.out.print(ANSI_BLUE);
            System.out.println("1. View Account Details");
            System.out.println("2. Update Account Status");
            System.out.println("3. Return to Main Menu");
            System.out.print(ANSI_PURPLE);
            System.out.print("Enter your choice: ");
            option = sc.nextInt();
            sc.nextLine();  // Consume newline character after input

            switch (option) {
                case 1:
                    // View Account Details
                    System.out.print("Enter Account ID: "); // Changed to Account ID
                    String accountId = sc.nextLine(); // Get Account ID instead of Customer ID
                    Account account = accountDAO.getAccountById(accountId); // Fetch account by account ID
                    if (account != null) {
                        System.out.println("Account ID: " + account.getAccountId());
                        System.out.println("Account Type: " + account.getType());
                        System.out.println("Balance: " + account.getBalance());
                        System.out.println("Status: " + account.getStatus());
                        System.out.println("Branch ID: " + account.getBranch().getBranchId());
                    } else {
                        System.out.println("No account found for Account ID: " + accountId); // Updated message
                    }
                    break;
                case 2:
                    // Update Account Status
                    System.out.print("Enter Account ID to update: ");
                    accountId = sc.nextLine(); // Get Account ID for status update
                    System.out.print("Enter new Status (Active/Inactive): ");
                    String newStatus = sc.nextLine();
                    accountDAO.updateAccountStatus(accountId, newStatus); // Update status using account ID
                    System.out.println("Account status updated successfully.");
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (option != 3);
    }


    // Loan Management Submenu
    private void manageLoanDetails() {
        Scanner sc = new Scanner(System.in);
        LoanDAO loanDAO = new LoanDAO();
        int option;
        do {
            System.out.print(ANSI_GREEN);
            System.out.println("\n*** Loan Management Menu ***");
            System.out.print(ANSI_BLUE);
            System.out.println("1. View All Loans");
            System.out.println("2. Update Loan Details");
            System.out.println("3. Return to Main Menu");
            System.out.print(ANSI_PURPLE);
            System.out.print("Enter your choice: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    // View All Loans
                    System.out.print("Enter Customer ID: ");
                    String customerId = sc.nextLine();
                    java.util.List<Loan> loans = loanDAO.getLoansByCustomerId(customerId);
                    if (loans != null && !loans.isEmpty()) {
                        for (Loan loan : loans) {
                            System.out.println("Loan ID: " + loan.getLoanId());
                            System.out.println("Loan Amount: " + loan.getAmount());
                            System.out.println("Loan Type: " + loan.getLoanType());
                            System.out.println("---------------------------");
                        }
                    } else {
                        System.out.println("No loans found for Customer ID: " + customerId);
                    }
                    break;

                case 2:
                    // Update Loan Details
                    System.out.print("Enter Loan ID to update: ");
                    String loanId = sc.nextLine();

                    // Retrieve the loan from the database using LoanDAO
                    Loan loan = loanDAO.getLoanById(loanId); // Ensure this method exists in LoanDAO

                    if (loan != null) {
                        System.out.print("Enter new Loan Amount: ");
                        double newAmount = sc.nextDouble();
                        sc.nextLine(); // Consume newline

                        loan.setAmount(newAmount);

                        // Update the loan in the database
                        loanDAO.updateLoan(loan);
                        System.out.println("Loan details updated successfully!");
                    } else {
                        System.out.println("Loan with ID " + loanId + " not found!");
                    }
                    break;

                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (option != 3);
    }


 // Transaction Management Submenu
 // Transaction Management Submenu
    private void manageTransactionDetails() {
        Scanner sc = new Scanner(System.in);
        TransactionDao transactionDAO = new TransactionDao(); // Ensure DAO is instantiated here
        int option;
        do {
            System.out.print(ANSI_GREEN);
            System.out.println("\n*** Transaction Management Menu ***");
            System.out.print(ANSI_BLUE);
            System.out.println("1. View Transaction History");
            System.out.println("2. Return to Main Menu");
            System.out.print(ANSI_PURPLE);
            System.out.print("Enter your choice: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    // View Transaction History
                	 System.out.print(ANSI_BLUE);
                    System.out.print("Enter Customer ID: "); // Change to Customer ID
                    String customerId = sc.nextLine(); // Use customerId instead of accountId
                    System.out.print(ANSI_GREEN);
                    System.out.println("Fetching transactions for Customer ID: " + customerId); // Debug statement
                    
                    // Fetch transactions from the database
                    List<Transaction> transactions = transactionDAO.getTransactionsByCustomerId(customerId);
                    if (transactions != null && !transactions.isEmpty()) {
                        for (Transaction transaction : transactions) {
                            System.out.println("Transaction ID: " + transaction.getTransactionId());
                            System.out.println("Amount: " + transaction.getAmount());
                            System.out.println("Type: " + transaction.getType());
                            System.out.println("Status: " + transaction.getStatus());
                            System.out.println("Account Details: " + transaction.getAccount());
                            
                            // Format the transaction date
                            LocalDateTime transactionDate = transaction.getTransactionDate();
                            if (transactionDate != null) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                System.out.println("Date: " + dateFormat.format(transactionDate));
                            }
                            
                            System.out.println("---------------------------");
                        }
                    } else {
                        System.out.println("No transactions found for Customer ID: " + customerId);
                    }
                    break;
                case 2:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (option != 2);
    }
    // Helper method to parse date
    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format! Please enter the date in dd/MM/yyyy format.");
            return null;
        }
    }
}
