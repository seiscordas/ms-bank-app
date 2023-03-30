package com.kl.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kl.account.config.AccountServiceConfig;
import com.kl.account.model.*;
import com.kl.account.service.client.ICardFeignClient;
import com.kl.account.service.client.ILoanFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kl.account.repository.AccountRepository;

import java.util.List;

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

	@Autowired
	ILoanFeignClient loansFeignClient;

	@Autowired
	ICardFeignClient cardsFeignClient;

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

	@PostMapping("/myCustomerDetails")
	public CustomerDetails myCustomerDetails(@RequestBody Customer customer) {
		Account accounts = accountRepository.findByCustomerId(customer.getCustomerId());
		List<Loan> loans = loansFeignClient.getLoansDetails(customer);
		List<Card> cards = cardsFeignClient.getCardDetails(customer);

		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loans);
		customerDetails.setCards(cards);

		return customerDetails;
	}


}

