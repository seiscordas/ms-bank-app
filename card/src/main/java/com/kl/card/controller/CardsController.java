/**
 * 
 */
package com.kl.card.controller;

import com.kl.card.model.Card;
import com.kl.card.model.Customer;
import com.kl.card.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author KL Systems
 *
 */

@RestController
public class CardsController {

	@Autowired
	private CardRepository cardRepository;

	@PostMapping("/myCards")
	public List<Card> getCardDetails(@RequestBody Customer customer) {
		return cardRepository.findByCustomerId(customer.getCustomerId());
	}
}
