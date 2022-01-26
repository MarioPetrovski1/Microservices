package com.iwlabs.fraud.service;

import com.iwlabs.clients.fraud.FraudCheckResponse;

public interface FraudCheckService {
	FraudCheckResponse isFraudelentCustomer(Integer customerId,String customerEmail);
}
