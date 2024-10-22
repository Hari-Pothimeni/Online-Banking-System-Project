package org.jpmc.OBS.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
    
    // Constructors, Getters, and Setters

	public Payment()
	{	}

	public Payment(String paymentId, String type, double amount, Date date, Loan loan) {
		super();
		this.paymentId = paymentId;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.loan = loan;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

}

	