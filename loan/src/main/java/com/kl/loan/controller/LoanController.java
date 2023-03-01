/**
 * 
 */
package com.kl.loan.controller;

import java.util.List;

import com.kl.loan.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kl.loan.model.Customer;
import com.kl.loan.model.Loan;
import com.kl.loan.repository.LoanRepository;

/**
 * @author Eazy Bytes
 *
 */

@RestController
public class LoanController {

	@Autowired
	private LoanRepository LoanRepository;

	@PostMapping("/myLoans")
	public List<Loan> getLoanDetails(@RequestBody Customer customer) {
		return LoanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
	}

}
