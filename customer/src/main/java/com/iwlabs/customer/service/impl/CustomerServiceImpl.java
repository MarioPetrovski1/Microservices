package com.iwlabs.customer.service.impl;

import com.iwlabs.customer.domain.Customer;
import com.iwlabs.customer.domain.dto.CustomerDTO;
import com.iwlabs.customer.mapper.CustomerMapper;
import com.iwlabs.customer.repositroy.CustomerRepository;
import com.iwlabs.customer.service.CustomerService;
import com.iwlabs.customer.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void registerCustomer(CustomerDTO customerRequestDTO) {
		Customer customer = customerMapper.dtoToEntity(customerRequestDTO);
		customerRepository.save(customer);
	}

	@Override
	public CustomerDTO getById(Integer id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isPresent()) {
			return customerMapper.entityToDto(customer.get());
		} else {
			throw new ResourceNotFoundException("Customer not found");
		}
	}

	@Override
	public void deleteCustomer(Integer id) {
		customerRepository.deleteById(id);
	}

	@Override
	public CustomerDTO updateCustomer(Integer id, CustomerDTO customerDTO) {
		CustomerDTO persistedCustomer = getById(id);
		Customer persistedEntity = customerMapper.dtoToEntity(persistedCustomer);
		persistedEntity.setId(id);
		customerMapper.mapRequestedFieldsForUpdate(persistedEntity,customerDTO);
		customerRepository.saveAndFlush(persistedEntity);
		return customerMapper.entityToDto(persistedEntity);
	}
}
