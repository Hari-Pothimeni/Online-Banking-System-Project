package org.jpmc.OBS.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // Uncomment this if customerId is auto-generated
    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "dob", nullable = false)
    @Temporal(TemporalType.DATE)  // Use Temporal to store only the date part
    private Date dob;  // Alternatively, consider using LocalDate if you are using Java 8 or later

    @Column(name = "account_id", nullable = false)  // Use account_id as a String reference
    private String accountId;  // String to store account ID

    // Constructors
    public Customer() {}

    public Customer(String customerId, String name, String phoneNumber, String address, Date dob, String accountId) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dob = dob;
        this.accountId = accountId;
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAccountId() {  // Getter for accountId
        return accountId;
    }

    public void setAccountId(String accountId) {  // Setter for accountId
        this.accountId = accountId;
    }

    // Method for setting dob using LocalDate if required
    public void setDob(LocalDate dob2) {
        // Handle LocalDate if required
    }

    public static boolean login(Scanner sc) {
        // Implement login logic here
        return false;
    }
}
