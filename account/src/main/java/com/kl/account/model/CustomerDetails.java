/**
 * 
 */
package com.kl.account.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author EazyBytes
 *
 */
@Getter
@Setter
@ToString
public class CustomerDetails {
	
	private Account accounts;
	private List<Loan> loans;
	private List<Card> cards;
	
	

}
