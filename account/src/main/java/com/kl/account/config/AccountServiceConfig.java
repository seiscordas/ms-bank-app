/**
 * 
 */
package com.kl.account.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author KL Sites
 *
 */
@Configuration
@ConfigurationProperties(prefix = "account")
@Getter @Setter @ToString
public class AccountServiceConfig {

	 private String msg;
	 private String buildVersion;
	 private Map<String, String> mailDetails;
	 private List<String> activeBranches;

}
