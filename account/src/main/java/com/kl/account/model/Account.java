package com.kl.account.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_account")
public class Account implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Column(name = "customer_id")
	private int customerId;
	@Column(name="account_number")
	@Id
	private long accountNumber;
	@Column(name="account_type")
	private String accountType;
	@Column(name = "branch_address")
	private String branchAddress;
	@Column(name = "create_dt")
	private LocalDate createDt;
}
