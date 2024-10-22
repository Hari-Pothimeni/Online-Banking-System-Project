package org.jpmc.OBS.dao;

import org.hibernate.Session;

import org.hibernate.Transaction;
import org.jpmc.OBS.entity.Customer;
import org.jpmc.OBS.entity.Loan;
import org.jpmc.OBSUtil.HibernateUtil;



import java.util.List;
import java.util.Scanner;

public class LoanDAO {

    // Save a new Loan object to the database
    public void saveLoan(Loan loan) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Save the loan object
            session.save(loan);
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get all Loans
    @SuppressWarnings("unchecked")
    public List<Loan> getAllLoans() {
        Transaction transaction = null;
        List<Loan> loans = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Get all Loan objects
            loans = session.createQuery("from Loan").list();
            // Commit the transaction (optional here, since we are reading)
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return loans;
    }

    // Update an existing Loan object
    public void updateLoan(Scanner scanner) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Update the loan object
            session.update(scanner);
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Approve a loan
    public void approveLoan(Scanner scanner) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Prompt the user to enter the loan ID to approve
            System.out.println("Enter Loan ID to approve:");
            Long loanId = scanner.nextLong();

            // Load the Loan object from the database
            Loan loan = session.get(Loan.class, loanId);

            if (loan == null) {
                System.out.println("Loan not found.");
                return;
            }

            // Update the loan status to "approved"
            loan.setStatus("approved");  // Assuming there's a 'status' field in the Loan entity

            // Save the updated loan object
            session.update(loan);

            // Commit the transaction
            transaction.commit();
            System.out.println("Loan approved successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Cancel a loan
    public Loan cancelLoan(Long loanId) { // Specify the type of loanId
        Transaction transaction = null;
        Loan loan = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Retrieve Loan object by loan ID
            loan = session.get(Loan.class, loanId);

            if (loan == null) {
                System.out.println("Loan not found with ID: " + loanId);
                return null;
            }

            // Update loan status to "canceled"
            loan.setStatus("canceled"); // Assuming the Loan entity has a 'status' field

            // Update the loan in the database
            session.update(loan);

            // Commit the transaction
            transaction.commit();
            System.out.println("Loan canceled successfully for ID: " + loanId);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return loan; // Returning the loan object may still be acceptable
    }

    // Get all Customers
    @SuppressWarnings("unchecked")
    public List<Loan> getAllloans() {
        Transaction transaction = null;
        List<Loan> loans = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Get all loan objects
            loans = session.createQuery("from loan").list();
            // Commit the transaction (optional here, since we are reading)
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return loans;
    }

    
    public void deleteloan(String loanId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Get the loan object to delete
            Loan loan = session.get(Loan.class, loanId);
            if (loan != null) {
                // Delete the loan object
                session.delete(loan);
                System.out.println("Customer is deleted successfully.");
            }
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Loan> getLoansByCustomerId(String customerId) {
        List<Loan> loans = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            loans = session.createQuery("FROM Loan l WHERE l.customer.customerId = :customerId", Loan.class)
                    .setParameter("customerId", customerId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loans;
    }

    // Update loan details
    public void updateLoan(Loan loan) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(loan);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public void updateLoan(String loanId, double newLoanAmount) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Fetch the loan by ID
            Loan loan = session.get(Loan.class, loanId);
            if (loan != null) {
                // Update the loan amount
                loan.setAmount(newLoanAmount);
                session.update(loan);
                transaction.commit(); // Commit the transaction
            } else {
                System.out.println("Loan not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace(); // Print the exception for debugging
        }
    }
    
    public Loan getLoanById(String loanId) {
        Loan loan = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            loan = session.get(Loan.class, loanId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loan;
    }


}