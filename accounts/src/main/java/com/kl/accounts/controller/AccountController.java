/**
 * 
 */
package com.kl.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kl.accounts.model.Account;
import com.kl.accounts.model.Customer;
import com.kl.accounts.repository.AccountRepository;

/**
 * @author KL Systems
 *
 */

@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;

	@PostMapping("/myAccount")
	public Account getAccountDetails(@RequestBody Customer customer) {
		return (Account) accountRepository.findByCustomerId(customer.getCustomerId());
	}

}

