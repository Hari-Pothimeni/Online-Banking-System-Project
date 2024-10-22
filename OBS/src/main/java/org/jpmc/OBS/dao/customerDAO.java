package org.jpmc.OBS.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jpmc.OBS.entity.Account;
import org.jpmc.OBS.entity.Customer;
import org.jpmc.OBSUtil.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class customerDAO {

    // Save a new Customer object to the database
	public boolean saveCustomer(Customer customer) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        session.save(customer); // Save the customer
	        transaction.commit(); // Commit the transaction
	        return true; // Return true if successful
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback(); // Rollback the transaction in case of an error
	        }
	        e.printStackTrace();
	        return false; // Return false if there was an error
	    } finally {
	        session.close(); // Always close the session
	    }
	}


    private boolean isValidDate(Date dob) {
        // Check if the date is not null
        return dob != null; // Make sure dob is not null
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true; // If parsing is successful, the date is valid
        } catch (ParseException e) {
            return false; // If parsing fails, it's an invalid date
        }
    }

    // Update an existing Customer object
    public void updateCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get a Customer object by ID
    public Customer getCustomerById(String customerId) {
        Transaction transaction = null;
        Customer customer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            customer = session.get(Customer.class, customerId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return customer;
    }

    // Get all Customers
    @SuppressWarnings("unchecked")
    public List<Customer> getAllCustomers() {
        Transaction transaction = null;
        List<Customer> customers = Collections.emptyList();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            customers = session.createQuery("FROM " + Customer.class.getName()).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return customers;
    }

    // Delete a Customer by ID
    public boolean deleteCustomer(String customerId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, customerId);
            if (customer != null) {
                session.delete(customer);
                transaction.commit();
                return true;
            } else {
                System.out.println("Customer not found!");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }


    public Account getAccountById(String accountId) {
        Transaction transaction = null;
        Account account = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            account = session.get(Account.class, accountId); // Fetch the account by ID
            if (account == null) {
                System.out.println("No account found with ID: " + accountId); // Log the absence
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return account; // Return the fetched account, or null if not found
    }


}
