package org.jpmc.OBS.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jpmc.OBS.entity.Admin;
import org.jpmc.OBSUtil.HibernateUtil;


import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminDAO {

    // Save Admin object to database
    public void saveAdmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Save the Admin object
            session.save(admin);
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
 		
 		
   
    private boolean isAdminIdExists(String admin_id) {
	// TODO Auto-generated method stub
	return false;
}

	private int getAdmincount() {
		// TODO Auto-generated method stub
		return 0;
	}

	// Update an existing Admin
    public void updateAdmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Update the Admin object
            session.update(admin);
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get Admin by ID
    public Admin getAdminById(String adminId) {
        Transaction transaction = null;
        Admin admin = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Get Admin object
            admin = session.get(Admin.class, adminId);
            // Commit the transaction (optional here, since we are reading)
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return admin;
    }

    // Get all Admins
    @SuppressWarnings("unchecked")
    public List<Admin> getAllAdmins() {
        Transaction transaction = null;
        List<Admin> admins = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Get all Admin objects
            admins = session.createQuery("from Admin").list();
            // Commit the transaction (optional here, since we are reading)
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return admins;
    }

    // Delete Admin by ID
    public void deleteAdmin(String adminId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Get Admin object
            Admin admin = session.get(Admin.class, adminId);
            if (admin != null) {
                // Delete the Admin object
                session.delete(admin);
                System.out.println("Admin is deleted");
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
}
