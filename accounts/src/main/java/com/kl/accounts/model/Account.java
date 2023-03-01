package com.kl.accounts.model;

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

	private int customerId;
	@Id
	private long accountNumber;
	private String accountType;
	private String branchAddress;
	private LocalDate createDt;
}
