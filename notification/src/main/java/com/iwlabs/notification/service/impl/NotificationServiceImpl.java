package com.iwlabs.notification.service.impl;

import com.iwlabs.clients.notification.NotificationRequest;
import com.iwlabs.notification.domain.Notification;
import com.iwlabs.notification.repository.NotificationRepository;
import com.iwlabs.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	public void send(NotificationRequest notificationRequest) {
		notificationRepository.save(
				Notification.builder()
						.toCustomerId(notificationRequest.getToCustomerId())
						.toCustomerEmail(notificationRequest.getToCustomerName())
						.sender("Mario")
						.message(notificationRequest.getMessage())
						.sentAt(LocalDateTime.now())
						.build()
		);
	}
}
