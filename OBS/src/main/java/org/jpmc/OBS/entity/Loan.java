package org.jpmc.OBS.entity;

import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "loan")

public class Loan {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private String loanId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "amount")
    private double amount;

    @Column(name = "approval_date")
    private Date approvalDate;
    
    // Constructors, Getters, and Setters


	public Loan()
	{ }

	public Loan(String loanId, Customer customer, String loanType, double amount, Date approvalDate) {
		super();
		this.loanId = loanId;
		this.customer = customer;
		this.loanType = loanType;
		this.amount = amount;
		this.approvalDate = approvalDate;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}

	public String Status() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setApprovalDate(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void setApprovalDate(LocalDate now) {
		// TODO Auto-generated method stub
		
	}
}	