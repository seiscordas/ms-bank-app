package com.kl.gatewayserver.filters;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {

	public static final String CORRELATION_ID = "kl-correlation-id";
	
	public String getCorrelationId(HttpHeaders requestHeaders) {
		if (requestHeaders.get(CORRELATION_ID) != null) {
			List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID);
			return Objects.requireNonNull(requestHeaderList).stream().findFirst().orElse(null);
		} else {
			return null;
		}
	}

	public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
		return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
	}

	public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
		return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
	}

}
