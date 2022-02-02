package com.iwlabs.customer.service.impl;

import com.iwlabs.clients.fraud.FraudClient;
import com.iwlabs.clients.notification.NotificationClient;
import com.iwlabs.clients.notification.NotificationRequest;
import com.iwlabs.customer.domain.Customer;
import com.iwlabs.customer.domain.dto.CustomerDTO;
import com.iwlabs.clients.fraud.FraudCheckResponse;
import com.iwlabs.customer.mapper.CustomerMapper;
import com.iwlabs.customer.repository.CustomerRepository;
import com.iwlabs.customer.service.CustomerService;
import com.iwlabs.customer.utils.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	FraudClient fraudClient;

	@Autowired
	NotificationClient notificationClient;

	@Override
	public void registerCustomer(CustomerDTO customerRequestDTO) {
		log.info("New customer registration {}",customerRequestDTO);
		Customer customer = customerMapper.dtoToEntity(customerRequestDTO);
		customerRepository.saveAndFlush(customer);

		FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId(),customer.getEmail());

		if(fraudCheckResponse.isFraudster()) {
			throw new IllegalStateException("Fraudster.");
		}

		NotificationRequest notificationRequest = new NotificationRequest(
				customer.getId(),
				customer.getEmail(),
				String.format("Hi %s, welcome",customer.getFirstName()));

		notificationClient.sendNotification(notificationRequest);
	}

	@Override
	public CustomerDTO getByUuid(String uuid) {
		log.info("find customer by uuid {}",uuid);
		Customer customer = findByUuid(uuid);
		return customerMapper.entityToDto(customer);
	}

	@Override
	public void deleteCustomer(String uuid) {
		log.info("delete customer by uuid {}",uuid);
		Customer entity = findByUuid(uuid);
		customerRepository.delete(entity);
	}

	@Override
	public CustomerDTO updateCustomer(String uuid, CustomerDTO customerDTO) {
		log.info("update customer by uuid {}",uuid);
		Customer entity = findByUuid(uuid);
		customerMapper.mapRequestedFieldsForUpdate(entity,customerDTO);
		customerRepository.saveAndFlush(entity);
		return customerMapper.entityToDto(entity);
	}

	@Override
	public List<CustomerDTO> findAll() {
		log.info("Find all customers");
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
			log.info("Customer with uuid {} is not found",uuid);
			throw new ResourceNotFoundException("Customer not found");
		}
	}
}
