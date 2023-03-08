/**
 * 
 */
package com.kl.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kl.account.config.AccountServiceConfig;
import com.kl.account.model.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kl.account.model.Account;
import com.kl.account.model.Customer;
import com.kl.account.repository.AccountRepository;

/**
 * @author KL Systems
 *
 */

@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	AccountServiceConfig accountServiceConfig;

	@PostMapping("/myAccount")
	public Account getAccountDetails(@RequestBody Customer customer) {
		return (Account) accountRepository.findByCustomerId(customer.getCustomerId());
	}

	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountServiceConfig.getMsg(), accountServiceConfig.getBuildVersion(),
				accountServiceConfig.getMailDetails(), accountServiceConfig.getActiveBranches());
		return ow.writeValueAsString(properties);
	}
	@GetMapping("/account/version")
	public String getVersion() throws JsonProcessingException {
		return "Vesão 1";
	}
}

