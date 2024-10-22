package org.jpmc.OBS.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.jpmc.OBS.entity.User;

import java.util.List;

public class UserDao {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public UserDao() {
        // Create an EntityManagerFactory and EntityManager for the "OBS" persistence unit
        entityManagerFactory = Persistence.createEntityManagerFactory("OBS");
        entityManager = entityManagerFactory.createEntityManager();
    }

    // Method to save a User
    public void saveUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    // Method to find a User by username (primary key)
    public User findUserByUsername(String username) {
        return entityManager.find(User.class, username);
    }

    // Method to update a User
    public void updateUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    // Method to delete a User
    public void deleteUser(String username) {
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, username);
            if (user != null) {
                entityManager.remove(user);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    // Method to retrieve all Users
    public List<User> findAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    // Close the EntityManager and EntityManagerFactory when done
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

	public void saveUser() {
		
	}
}
