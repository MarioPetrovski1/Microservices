package com.iwlabs.notification.controller;

import com.iwlabs.clients.notification.NotificationRequest;
import com.iwlabs.notification.service.NotificationService;
import com.iwlabs.notification.utils.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.NOTIFICATION)
public class NotificationController {

	@Autowired
	NotificationService notificationService;

	@PostMapping
	public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
		notificationService.send(notificationRequest);
	}
}
