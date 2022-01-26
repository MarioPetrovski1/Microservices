package com.iwlabs.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("fraud")
public interface FraudClient {

	@GetMapping("api/v1/fraud-check/{customerId}")
	FraudCheckResponse isFraudster(@PathVariable(name = "customerId") Integer customerId,@RequestParam(name = "customerEmail") String customerEmail);

}
