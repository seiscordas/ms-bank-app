package com.kl.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kl.account.config.AccountServiceConfig;
import com.kl.account.model.*;
import com.kl.account.service.client.ICardFeignClient;
import com.kl.account.service.client.ILoanFeignClient;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	//@CircuitBreaker(name = "detailsForCustomerSupportApp", fallbackMethod = "myCustomerDetailsFallBack")
	@Retry(name = "retryForCustomerDetails", fallbackMethod = "myCustomerDetailsFallBack")
	public CustomerDetails myCustomerDetails(@RequestHeader("kl-correlation-id") String correlationId, @RequestBody Customer customer) {
		Account accounts = accountRepository.findByCustomerId(customer.getCustomerId());
		List<Loan> loans = loansFeignClient.getLoansDetails(correlationId, customer);
		List<Card> cards = cardsFeignClient.getCardDetails(correlationId, customer);

		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loans);
		customerDetails.setCards(cards);

		return customerDetails;
	}

	private CustomerDetails myCustomerDetailsFallBack(@RequestHeader("kl-correlation-id") String correlationId, Customer customer, Throwable t) {
		Account accounts = accountRepository.findByCustomerId(customer.getCustomerId());
		List<Loan> loans = loansFeignClient.getLoansDetails(correlationId, customer);
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loans);
		return customerDetails;
	}

	@GetMapping("/sayHello")
	@RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
	public String sayHello() {
		return "Hello, Welcome to KL Systems";
	}

	private String sayHelloFallback(Throwable t) {
		return "Hi, Welcome to KL Systems";
	}
}

