package org.jpmc.OBS.Module;

import org.jpmc.OBS.dao.customerDAO;


import org.jpmc.OBS.entity.Customer;
import org.jpmc.OBSUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.*;


public class AdminModule {
	
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	 public static final String ANSI_CYAN = "\u001B[36m";


    private customerDAO customerDAO;

    public static void adminMenu(String adminUsername) {
        AdminModule adminModule = new AdminModule();
        adminModule.customerDAO = new customerDAO(); // Initialize DAO
        adminModule.displayAdminMenu(adminUsername);
    }

    private void displayAdminMenu(String adminUsername) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
        	
        	System.out.print(ANSI_PURPLE);
            System.out.println("\n*** Admin Menu ***");
            System.out.print(ANSI_RED);
            System.out.println("Welcome, Admin " + adminUsername);
            System.out.print(ANSI_BLUE);
            System.out.println("1. View All Customers");
            System.out.println("2. View Customer Details");
            System.out.println("3. Add Customer");
            System.out.println("4. Update Customer Details");
            System.out.println("5. Delete Customer");
            System.out.println("6. Logout");
            
            
            System.out.println(ANSI_CYAN);
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                	
                    viewAllCustomers();
                    break;
                case 2:
                    viewCustomerDetails();
                    break;
                case 3:
                	 addCustomer();
                    
                    break;
                case 4:
                	updateCustomerDetails();
                    
                    break;
                case 5:
                	deleteCustomer();
                    
                    break;
                    
                case 6:
                	System.out.println("Logging out...");
                    break;
                default:
                	System.out.print(ANSI_RED);
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 6);
    }

    // View All Customers
    private void viewAllCustomers() {
    	System.out.print(ANSI_GREEN);
    	List<Customer> customers = customerDAO.getAllCustomers(); // Assuming this fetches all customers
        System.out.println("Fetching all customers...");
        for (Customer customer : customers) {
        	System.out.print(ANSI_BLACK);
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Customer Address: " + customer.getAddress());
            System.out.println("Customer Dob: " + customer.getDob());
            System.out.println("Customer Phone Number: " + customer.getPhoneNumber());
            System.out.println("Customer Account Id: " + customer.getAccountId());
            
            // Print an empty line for separation
            System.out.println("*************************************"); // This adds a gap
        }
    }

    // View Customer Details
    private void viewCustomerDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.print(ANSI_GREEN);
        System.out.print("Enter Customer ID to view: ");
        String customerId = sc.nextLine();

        Optional<Customer> optionalCustomer = Optional.ofNullable(customerDAO.getCustomerById(customerId));

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            System.out.print(ANSI_BLUE);
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Phone: " + customer.getPhoneNumber());
            System.out.println("Address: " + customer.getAddress());
            // Additional details can be shown as needed
        } else {
            System.out.println("Customer with ID " + customerId + " not found!");
        }
    }

    // Update Customer Details
    private void updateCustomerDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.print(ANSI_GREEN);
        System.out.print("Enter Customer ID to update: ");
        String customerId = sc.nextLine();

        Optional<Customer> optionalCustomer = Optional.ofNullable(customerDAO.getCustomerById(customerId));

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            System.out.print(ANSI_PURPLE);
//            System.out.print("Enter new Name: ");
//            String name = sc.nextLine();
            System.out.print("Enter new Phone Number: ");
            String phoneNumber = sc.nextLine();
            System.out.print("Enter new Address: ");
            String address = sc.nextLine();

            //customer.setName(name);
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);

            customerDAO.updateCustomer(customer); // Save the updated customer details
            System.out.print(ANSI_PURPLE);
            System.out.println("Customer details updated successfully!");
        } else {
        	System.out.print(ANSI_RED);
            System.out.println("Customer not found!");
        }
    }

    // Delete Customer
    private void deleteCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print(ANSI_GREEN);
        System.out.print("Enter Customer ID to delete: ");
        String customerId = sc.nextLine();

        if (customerDAO.deleteCustomer(customerId)) {
        	System.out.print(ANSI_RED);
            System.out.println("Customer deleted successfully!");
        } else {
            System.out.println("Customer with ID " + customerId + " not found!");
        }
    }
    
    //add customer
    
    private void addCustomer() {
        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer();
        System.out.print(ANSI_BLUE);
        System.out.print("Enter Customer ID: ");
        customer.setCustomerId(sc.nextLine());

        // Check if customer already exists
        if (customerDAO.getCustomerById(customer.getCustomerId()) != null) {
            System.out.println("Customer with this ID already exists. Please use a different ID.");
            return;
        }

        System.out.print("Enter Customer Name: ");
        customer.setName(sc.nextLine());

        System.out.print("Enter Customer Address: ");
        customer.setAddress(sc.nextLine());

        System.out.print("Enter Customer Phone Number: ");
        customer.setPhoneNumber(sc.nextLine());

        System.out.print("Enter Date of Birth (dd/MM/yyyy): ");
        String dobInput = sc.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dob = dateFormat.parse(dobInput);  // Parse the date
            customer.setDob(dob);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            return;
        }

        // Enter Account ID and check if it exists
        System.out.print("Enter Account ID: ");
        String accountId = sc.nextLine();
        if (customerDAO.getAccountById(accountId) == null) {
            System.out.println("Account with this ID does not exist. Please enter a valid Account ID.");
            return;
        }
        customer.setAccountId(accountId); // Assuming accountId is a field in Customer

        // Call DAO to save customer
        if (customerDAO.saveCustomer(customer)) {
        	System.out.print(ANSI_GREEN);
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Failed to add customer.");
        }
    }
}