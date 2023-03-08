/**
 * 
 */
package com.kl.card.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kl.card.config.CardServiceConfig;
import com.kl.card.model.Card;
import com.kl.card.model.Customer;
import com.kl.card.model.Properties;
import com.kl.card.repository.CardRepository;
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
public class CardsController {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	CardServiceConfig cardServiceConfig;

	@PostMapping("/myCards")
	public List<Card> getCardDetails(@RequestBody Customer customer) {
		return cardRepository.findByCustomerId(customer.getCustomerId());
	}

	@GetMapping("/card/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(cardServiceConfig.getMsg(), cardServiceConfig.getBuildVersion(),
				cardServiceConfig.getMailDetails(), cardServiceConfig.getActiveBranches());
		return ow.writeValueAsString(properties);
	}
}
