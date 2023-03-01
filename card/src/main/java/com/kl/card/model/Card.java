package com.kl.card.model;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.*;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_card")
public class Card implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cardId;
	private int customerId;
	private String cardNumber;
	private String cardType;
	private int totalLimit;
	private int amountUsed;
	private int availableAmount;
	private Date createDt;
}
