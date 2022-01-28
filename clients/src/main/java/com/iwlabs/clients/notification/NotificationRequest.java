package com.iwlabs.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
	private Integer toCustomerId;
	private String toCustomerName;
	private String message;
}
