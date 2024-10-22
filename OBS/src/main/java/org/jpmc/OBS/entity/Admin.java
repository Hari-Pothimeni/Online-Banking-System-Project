package org.jpmc.OBS.entity;

import java.util.Scanner;


import jakarta.persistence.*;


@Entity
@Table(name = "admin")
public class Admin {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "name")
    private String name;

   
    
    //@OneToMany(mappedBy = "admin")
    //private Branch branch;
    
 // Constructors, Getters, and Setters
    

	public Admin()
	{ }

	public Admin(String adminId, String name, String username, String password) {
		super();
		this.adminId = adminId;
		this.name = name;
		
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public static boolean login(Scanner sc) {
		// TODO Auto-generated method stub
		return false;
	}

}
   
  