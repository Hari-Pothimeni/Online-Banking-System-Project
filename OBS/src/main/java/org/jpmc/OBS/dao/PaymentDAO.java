package org.jpmc.OBS.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jpmc.OBS.entity.Payment;
import org.jpmc.OBSUtil.HibernateUtil;

@SuppressWarnings("unused")
public class PaymentDAO {
    
    // Save a new Payment to the database
    public void createPayment(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(payment);  // Persist the payment object
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback if there’s an error
            }
            e.printStackTrace();
        }
    }

    // Retrieve a Payment by its ID
    public Payment getPayment(Long paymentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Payment.class, paymentId);  // Get the payment by its ID
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieve all Payments associated with a specific Loan ID
    @SuppressWarnings("unchecked")
	public List<Payment> getPaymentsByLoan(Long loanId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Payment p WHERE p.loan.loanId = :loanId";  // HQL query
            Query query = session.createQuery(hql);  // Create the query
            query.setParameter("loanId", loanId);  // Set the loanId parameter
            return ((javax.persistence.Query) query).getResultList();  // Get the list of payments
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieve all Payments from the database
    @SuppressWarnings("unchecked")
	public List<Payment> getAllPayments() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Payment";  // HQL query to get all payments
            Query query = session.createQuery(hql);  // Create the query
            return ((javax.persistence.Query) query).getResultList();  // Get the list of payments
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing Payment
    public void updatePayment(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(payment);  // Update the payment object
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback if there’s an error
            }
            e.printStackTrace();
        }
    }

    // Delete a Payment by its ID
    public void deletePayment(Long paymentId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Payment payment = session.get(Payment.class, paymentId);  // Get the payment object by ID
            if (payment != null) {
                session.delete(payment);  // Delete the payment object
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback if there’s an error
            }
            e.printStackTrace();
        }
    }
}
