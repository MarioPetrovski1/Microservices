package com.iwlabs.customer.mapper;

import com.iwlabs.customer.domain.Customer;
import com.iwlabs.customer.domain.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerMapper implements GeneralMapper<CustomerDTO, Customer> {
	@Override
	public CustomerDTO entityToDto(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setUuid(customer.getUuid());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setCreatedOn(customer.getCreatedOn());
		return customerDTO;
	}

	@Override
	public Customer dtoToEntity(CustomerDTO customerDTO) {
		Customer customer = Customer.builder()
				.firstName(customerDTO.getFirstName())
				.lastName(customerDTO.getLastName())
				.email(customerDTO.getEmail())
				.build();
		return customer;
	}

	@Override
	public void mapRequestedFieldsForUpdate(Customer customer, CustomerDTO customerDTO) {
		if(!Objects.isNull(customerDTO.getFirstName())) {
			customer.setFirstName(customerDTO.getFirstName());
		}

		if(!Objects.isNull(customerDTO.getLastName())) {
			customer.setLastName(customerDTO.getLastName());
		}

		if(!Objects.isNull(customerDTO.getEmail())) {
			customer.setEmail(customerDTO.getEmail());
		}
	}
}
