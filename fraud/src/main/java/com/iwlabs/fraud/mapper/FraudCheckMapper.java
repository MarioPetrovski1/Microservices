package com.iwlabs.fraud.mapper;

import com.iwlabs.clients.fraud.FraudCheckResponse;
import com.iwlabs.fraud.domain.FraudCheckHistory;
import org.springframework.stereotype.Component;

@Component
public class FraudCheckMapper implements GeneralMapper<FraudCheckResponse, FraudCheckHistory> {
	@Override
	public FraudCheckResponse entityToDto(FraudCheckHistory fraudCheckHistory) {
		return FraudCheckResponse.builder()
				.customerId(fraudCheckHistory.getCustomerId())
				.isFraudster(fraudCheckHistory.getIsFraudster())
				.createdOn(fraudCheckHistory.getCreatedOn())
				.build();
	}

	@Override
	public FraudCheckHistory dtoToEntity(FraudCheckResponse fraudCheckResponse) {
		return FraudCheckHistory.builder()
				.customerId(fraudCheckResponse.getCustomerId())
				.isFraudster(fraudCheckResponse.isFraudster())
				.createdOn(fraudCheckResponse.getCreatedOn())
				.build();
	}
}
