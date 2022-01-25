package com.iwlabs.fraud.service;

import com.iwlabs.fraud.domain.dto.FraudCheckResponse;

public interface FraudCheckService {
	FraudCheckResponse isFraudelentCustomer(Integer customerId);
}
