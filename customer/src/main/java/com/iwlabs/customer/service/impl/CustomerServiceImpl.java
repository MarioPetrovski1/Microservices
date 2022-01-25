package com.iwlabs.customer.service.impl;

import com.iwlabs.customer.domain.Customer;
import com.iwlabs.customer.domain.dto.CustomerDTO;
import com.iwlabs.customer.domain.dto.FraudCheckResponse;
import com.iwlabs.customer.mapper.CustomerMapper;
import com.iwlabs.customer.repository.CustomerRepository;
import com.iwlabs.customer.service.CustomerService;
import com.iwlabs.customer.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public void registerCustomer(CustomerDTO customerRequestDTO) {
		Customer customer = customerMapper.dtoToEntity(customerRequestDTO);
		customerRepository.saveAndFlush(customer);
		FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://localhost:8082/api/v1/fraud-check/{customerId}",
				FraudCheckResponse.class,
				customer.getId());

		if(fraudCheckResponse.isFraudster()) {
			throw new IllegalStateException("Fraudster.");
		}
	}

	@Override
	public CustomerDTO getByUuid(String uuid) {
		Customer customer = findByUuid(uuid);
		return customerMapper.entityToDto(customer);
	}

	@Override
	public void deleteCustomer(String uuid) {
		Customer entity = findByUuid(uuid);
		customerRepository.delete(entity);
	}

	@Override
	public CustomerDTO updateCustomer(String uuid, CustomerDTO customerDTO) {
		Customer entity = findByUuid(uuid);
		customerMapper.mapRequestedFieldsForUpdate(entity,customerDTO);
		customerRepository.saveAndFlush(entity);
		return customerMapper.entityToDto(entity);
	}

	@Override
	public List<CustomerDTO> findAll() {
		List<Customer> allCustomers = customerRepository.findAll();
		List<CustomerDTO> allCustomerMapped = new ArrayList<>();
		allCustomers.forEach(customer -> allCustomerMapped.add(customerMapper.entityToDto(customer)));
		return allCustomerMapped;
	}


	private Customer findByUuid(String uuid) {
		Optional<Customer> customer = customerRepository.findByUuid(uuid);
		if(customer.isPresent()) {
			return customer.get();
		} else {
			throw new ResourceNotFoundException("Customer not found");
		}
	}
}
