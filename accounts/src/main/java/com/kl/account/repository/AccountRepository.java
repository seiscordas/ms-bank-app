package com.kl.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kl.account.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	Account findByCustomerId(int customerId);
}
