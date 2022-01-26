package com.iwlabs.fraud.service.impl;

import com.iwlabs.clients.fraud.FraudCheckResponse;
import com.iwlabs.fraud.domain.FraudCheckHistory;
import com.iwlabs.fraud.mapper.FraudCheckMapper;
import com.iwlabs.fraud.repository.FraudCheckHistoryRepository;
import com.iwlabs.fraud.service.FraudCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FraudCheckServiceImpl implements FraudCheckService {

	@Autowired
	FraudCheckHistoryRepository fraudCheckHistoryRepository;

	@Autowired
	FraudCheckMapper fraudCheckMapper;

	@Override
	public FraudCheckResponse isFraudelentCustomer(Integer customerId,String customerEmail) {
		boolean isFraudser = false;

		if(!customerEmail.contains("@")) {
			isFraudser = true;
		}

		FraudCheckHistory history = FraudCheckHistory.builder()
						.customerId(customerId)
						.isFraudster(isFraudser)
						.createdOn(new Date())
						.build();
		fraudCheckHistoryRepository.save(history);
		return fraudCheckMapper.entityToDto(history);
	}

}
