package com.kl.account.service.client;

import com.kl.account.model.Card;
import com.kl.account.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("card")
public interface ICardFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "myCards", consumes = "application/json")
	List<Card> getCardDetails(@RequestHeader("kl-correlation-id") String correlationId, @RequestBody Customer customer);
}
