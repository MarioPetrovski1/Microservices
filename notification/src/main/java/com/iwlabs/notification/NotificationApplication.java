package com.iwlabs.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.iwlabs.notification",
		"com.iwlabs.amqp"
})
public class NotificationApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class,args);
	}
}
