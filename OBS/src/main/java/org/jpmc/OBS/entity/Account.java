package org.jpmc.OBS.entity;

import java.time.LocalDate; // Use java.time for better date handling
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "balance")
    private double balance;

    @Column(name = "status")
    private String status;

    @Column(name = "opening_date")
    private LocalDate openingDate; // Use LocalDate

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    // Constructors, Getters, and Setters
    public Account() { }

    public Account(String accountId, String accountNumber, String type, double balance, String status, LocalDate openingDate, Branch branch) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.openingDate = openingDate;
        this.branch = branch;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                ", openingDate=" + openingDate +
                ", branch=" + (branch != null ? branch.getBranchId() : "null") + // Assuming branch has a getBranchId method
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 &&
                accountId.equals(account.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance);
    }
}
