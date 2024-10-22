package org.jpmc.OBS.authentication;

import org.hibernate.Session;

import org.hibernate.Transaction;
import org.jpmc.OBS.entity.User;
import org.jpmc.OBS.Module.AdminModule;
import org.jpmc.OBS.Module.CustomerModule;
import org.jpmc.OBSUtil.HibernateUtil;

import java.util.Scanner;

public class Login {

	public void login() {
	    // Create session and transaction
	    Session session = null;
	    Transaction transaction = null;
	    Scanner sc = new Scanner(System.in);

	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction();

	        System.out.println();
	        System.out.print("Enter Username: ");
	        String uname = sc.next();
	        System.out.print("Enter Password: ");
	        String pword = sc.next();

	        // Retrieve user from database
	        User user = session.byId(User.class).load(uname);
	        if (user != null && user.getPassword().equals(pword)) {
	        	
	            System.out.println("  *****Login successful!*****    ");

	            // Here, user type comparison is not case sensitive
	            String userType = user.getUsertype().toLowerCase();
	            switch (userType) {
	                case "admin":
	                    AdminModule.adminMenu(uname);
	                    break;
	                case "customer":
	                    CustomerModule customerModule = new CustomerModule();  // Pass necessary dependencies if needed
	                    customerModule.customerMenu(uname);
	                    break;
	                default:
	                    System.out.println("Unknown user type.");
	            }

	        } else {
	            System.out.println("Invalid credentials!");
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}
}
