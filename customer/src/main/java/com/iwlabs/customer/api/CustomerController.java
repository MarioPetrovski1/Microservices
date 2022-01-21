package com.iwlabs.customer.api;


import com.iwlabs.customer.domain.Customer;
import com.iwlabs.customer.domain.dto.CustomerDTO;
import com.iwlabs.customer.service.CustomerService;
import com.iwlabs.customer.utils.Endpoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoints.CUSTOMER)
@Slf4j
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping
	public List<CustomerDTO> findAll() {
		return customerService.findAll();
	}

	@PostMapping
	public void registerCustomer(@RequestBody CustomerDTO customerRequest) {
		log.info("New customer registration {}",customerRequest);
		customerService.registerCustomer(customerRequest);
	}

	@GetMapping("/{uuid}")
	public CustomerDTO getByUuid(@PathVariable String uuid) {
		return customerService.getByUuid(uuid);
	}

	@DeleteMapping("/{uuid}")
	public void deleteCustomer(@PathVariable String uuid) {
		customerService.deleteCustomer(uuid);
	}

	@PutMapping("/{uuid}")
	public CustomerDTO updateCustomer(@PathVariable String uuid,@RequestBody CustomerDTO customerDTO) {
		return customerService.updateCustomer(uuid,customerDTO);
	}


}
