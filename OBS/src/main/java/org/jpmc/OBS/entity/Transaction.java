package org.jpmc.OBS.entity;

import java.time.LocalDateTime; // Using LocalDateTime instead of Date
import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    // Uncomment if you want to use auto-generation for IDs
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "status")
    private String status;

    @Column(name = "transaction_date") // Adjust this to match your database column name
    private LocalDateTime transactionDate; // Changed to LocalDateTime

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "customer_id")  // This assumes you have a Customer entity
    private Customer customer; // Reference to the Customer entity

    @ManyToOne
    @JoinColumn(name = "account_id") // Assuming you want to link to an Account
    private Account account; // Reference to the Account entity

    // Constructors, Getters, and Setters
    
    public Transaction() { }

    public Transaction(String transactionId, double amount, String status, LocalDateTime transactionDate, String type,
                       Customer customer, Account account) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.status = status;
        this.transactionDate = transactionDate;
        this.type = type;
        this.customer = customer;
        this.account = account;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTransactionDate() { // Updated to LocalDateTime
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) { // Updated to LocalDateTime
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
