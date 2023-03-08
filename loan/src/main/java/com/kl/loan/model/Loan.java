package com.kl.loan.model;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.*;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_loan")
public class Loan implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanNumber;
	private int customerId;
	private Date startDt;
	private String loanType;
	private int totalLoan;
	private int amountPaid;
	private int outstandingAmount;
	private String createDt;
}
