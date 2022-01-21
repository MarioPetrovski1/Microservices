package com.iwlabs.customer.service;

import com.iwlabs.customer.domain.Customer;
import com.iwlabs.customer.domain.dto.CustomerDTO;

import java.util.List;


public interface CustomerService {
	void registerCustomer(CustomerDTO customerRequestDTO);
	CustomerDTO getByUuid(String uuid);
	void deleteCustomer(String uuid);
	CustomerDTO updateCustomer(String uuid,CustomerDTO customerDTO);
	List<CustomerDTO> findAll();
}
