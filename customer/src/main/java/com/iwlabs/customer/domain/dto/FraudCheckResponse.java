package com.iwlabs.customer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudCheckResponse {
	private Integer customerId;
	private boolean isFraudster;
	private Date createdOn;
}
