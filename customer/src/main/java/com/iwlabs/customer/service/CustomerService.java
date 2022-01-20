package com.iwlabs.customer.service;

import com.iwlabs.customer.domain.dto.CustomerDTO;


public interface CustomerService {
	void registerCustomer(CustomerDTO customerRequestDTO);
	CustomerDTO getById(Integer id);
	void deleteCustomer(Integer id);
	CustomerDTO updateCustomer(Integer id,CustomerDTO customerDTO);
}
