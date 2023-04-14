package com.kl.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kl.account.config.AccountServiceConfig;
import com.kl.account.model.*;
import com.kl.account.service.client.ICardFeignClient;
import com.kl.account.service.client.ILoanFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.core.annotation.Timed;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kl.account.repository.AccountRepository;

import java.util.List;

/**
 * @author KL Systems
 */

@RestController
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountServiceConfig accountServiceConfig;

    @Autowired
    ILoanFeignClient loansFeignClient;

    @Autowired
    ICardFeignClient cardsFeignClient;

    @Autowired
    private ObservationRegistry observationRegistry;

    @PostMapping("/myAccount")
    @Timed(value = "getAccountDetails.time", description = "Time taken to return Account Details")
    public Account getAccountDetails(@RequestBody Customer customer) {
        Account accountDetails = (Account) accountRepository.findByCustomerId(customer.getCustomerId());
        return Observation
                .createNotStarted("getAccountDetails", observationRegistry)
                .observe(() -> accountDetails);
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

        return Observation
                .createNotStarted("myCustomerDetails", observationRegistry)
                .observe(() -> customerDetails);
    }

    private CustomerDetails myCustomerDetailsFallBack(@RequestHeader("kl-correlation-id") String correlationId, Customer customer, Throwable t) {
        Account accounts = accountRepository.findByCustomerId(customer.getCustomerId());
        List<Loan> loans = loansFeignClient.getLoansDetails(correlationId, customer);
        CustomerDetails customerDetails = new CustomerDetails();

        customerDetails.setAccounts(accounts);
        customerDetails.setLoans(loans);

        return Observation
                .createNotStarted("myCustomerDetails", observationRegistry)
                .observe(() -> customerDetails);
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

