package org.jpmc.OBS.dao;


import org.hibernate.Session;
import org.hibernate.Transaction; // Make sure to import Transaction correctly
import org.jpmc.OBSUtil.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class TransactionDao {

    // Get all transactions
//    public List<Transaction> getAllTransactions() {
//        List<Transaction> transactions = null;
//        Transaction transaction = null;
//        
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            // Start a Hibernate transaction (not the entity transaction)
//            transaction = session.beginTransaction();
//            
//            // Get all Transaction objects from the database
//            transactions = session.createQuery("from Transaction", Transaction.class).list();
//            
//            // Commit the transaction (optional for reads)
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//        
//        return transactions;
//    }

    // Check status of transaction by ID
    public void checkStatusByID(String transactionId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a Hibernate transaction
            transaction = session.beginTransaction();

            // Retrieve the Transaction object by its ID
            Transaction fetchedTransaction = session.get(Transaction.class, transactionId);

            if (fetchedTransaction != null) {
                // Print the status of the transaction
                System.out.println("Transaction status: " + fetchedTransaction.getStatus());
            } else {
                System.out.println("Transaction not found with ID: " + transactionId);
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

    // Update transaction status by ID
    public void updateStatusByID(Scanner scanner) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            System.out.print("Enter Transaction ID: ");
            String transactionId = scanner.nextLine();
            System.out.print("Enter new status: ");
            String newStatus = scanner.nextLine();

            Transaction fetchedTransaction = session.get(Transaction.class, transactionId);

            if (fetchedTransaction != null) {
                // Update the status of the transaction
                ((org.jpmc.OBS.entity.Transaction) fetchedTransaction).setStatus(newStatus);
                session.update(fetchedTransaction); // Save changes
                transaction.commit();
                System.out.println("Transaction status updated successfully!");
            } else {
                System.out.println("Transaction not found with ID: " + transactionId);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    // Get transactions by account ID
    public List<org.jpmc.OBS.entity.Transaction> getTransactionsByAccountId(String accountId) {
        List<org.jpmc.OBS.entity.Transaction> transactions = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transactions = session.createQuery("from org.jpmc.OBS.entity.Transaction where account.accountId = :accountId", org.jpmc.OBS.entity.Transaction.class)
                    .setParameter("accountId", accountId).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
    // Get transactions by customer ID
    public List<org.jpmc.OBS.entity.Transaction> getTransactionsByCustomerId(String customerId) {
        List<org.jpmc.OBS.entity.Transaction> transactions = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transactions = session.createQuery(
                "FROM org.jpmc.OBS.entity.Transaction t WHERE t.customer.customerId = :customerId", 
                org.jpmc.OBS.entity.Transaction.class)
                .setParameter("customerId", customerId)
                .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
    public List<org.jpmc.OBS.entity.Transaction> getAllTransactions() {
        List<org.jpmc.OBS.entity.Transaction> transactions = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transactions = session.createQuery("from org.jpmc.OBS.entity.Transaction", org.jpmc.OBS.entity.Transaction.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}