package com.iwlabs.customer.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {
	private String uuid;
	private String firstName;
	private String lastName;
	private String email;
	private Date createdOn;
}
