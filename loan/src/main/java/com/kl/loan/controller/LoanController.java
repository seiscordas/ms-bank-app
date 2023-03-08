/**
 * 
 */
package com.kl.loan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kl.loan.config.LoanServiceConfig;
import com.kl.loan.model.Customer;
import com.kl.loan.model.Loan;
import com.kl.loan.model.Properties;
import com.kl.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author KL Systems
 *
 */

@RestController
public class LoanController {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	LoanServiceConfig loanServiceConfig;

	@PostMapping("/myLoans")
	public List<Loan> getLoanDetails(@RequestBody Customer customer) {
		return loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
	}

	@GetMapping("/loan/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(loanServiceConfig.getMsg(), loanServiceConfig.getBuildVersion(),
				loanServiceConfig.getMailDetails(), loanServiceConfig.getActiveBranches());
		return ow.writeValueAsString(properties);
	}
}