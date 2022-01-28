package com.iwlabs.notification.service;

import com.iwlabs.clients.notification.NotificationRequest;

public interface NotificationService {
	void send(NotificationRequest notificationRequest);
}
