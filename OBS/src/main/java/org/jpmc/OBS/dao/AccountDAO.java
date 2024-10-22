package org.jpmc.OBS.dao;

import org.jpmc.OBS.entity.Account;
import org.jpmc.OBSUtil.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDAO {
    private EntityManager entityManager;

    // Create a new account
    public void createAccount(Account account) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(account);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    // Get account by accountId
    public Account getAccountById(String accountId) {
        Account account = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            account = session.createQuery("FROM Account WHERE accountId = :accountId", Account.class)
                    .setParameter("accountId", accountId)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    // Fetch all accounts
    public java.util.List<Account> getAllAccounts() {
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a", Account.class);
        return query.getResultList();
    }

    // Update an existing account
    public void updateAccount(Account account) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(account);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    // Delete an account by accountId
    public void deleteAccount(String accountId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account account = session.get(Account.class, accountId);
            if (account != null) {
                session.delete(account);
                transaction.commit();
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Update account status by accountId
    public void updateAccountStatus(String accountId, String newStatus) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account account = session.get(Account.class, accountId);
            if (account != null) {
                account.setStatus(newStatus);
                session.update(account);
                transaction.commit();
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
