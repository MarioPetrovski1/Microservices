package com.iwlabs.fraud.api;

import com.iwlabs.fraud.domain.dto.FraudCheckResponse;
import com.iwlabs.fraud.service.FraudCheckService;
import com.iwlabs.fraud.utils.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.FRAUD)
public class FraudController {

	@Autowired
	FraudCheckService fraudCheckService;

	@GetMapping("/{customerId}")
	public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
		return fraudCheckService.isFraudelentCustomer(customerId);
	}
}
