package org.jpmc.OBS.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jpmc.OBS.entity.Branch;
import hms.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class BranchDAO {

    // Method to retrieve all branches
    public void getAllBranches() {
        // Try-with-resources block to auto-close the session
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve all branches
            List<Branch> branches = session.createQuery("from Branch").list();

            // Display branches
            System.out.println("\nAll Branches:");
            System.out.printf("%-10s %-20s%n", "Branch ID", "Branch Name");

            for (Branch branch : branches) {
                System.out.printf("%-10s %-20s%n", branch.getBranchId(), branch.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to save a branch to the database
    public void saveBranch(Branch branch) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Save the branch object
            session.save(branch);

            // Commit the transaction
            transaction.commit();

            System.out.println("Branch added successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Method to update an existing branch
    public void updateBranch(Branch branch) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Update the branch object
            session.update(branch);

            // Commit the transaction
            transaction.commit();

            System.out.println("Branch updated successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Method to retrieve a branch by ID
    public Branch getBranchById(String branchId) {
        Branch branch = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Get branch object by ID
            branch = session.get(Branch.class, branchId);

            if (branch != null) {
                System.out.println("Branch found: " + branch.getName());
            } else {
                System.out.println("Branch not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return branch;
    }

    // Method to delete a branch
    public void deleteBranch(String branchId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Retrieve the branch to be deleted
            Branch branch = session.get(Branch.class, branchId);

            if (branch != null) {
                // Delete the branch
                session.delete(branch);

                // Commit the transaction
                transaction.commit();

                System.out.println("Branch deleted successfully.");
            } else {
                System.out.println("Branch not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	public void updateBranch(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	public void addBranch(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}
}
