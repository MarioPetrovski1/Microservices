package com.iwlabs.customer.api;


import com.iwlabs.customer.domain.dto.CustomerDTO;
import com.iwlabs.customer.service.CustomerService;
import com.iwlabs.customer.utils.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoints.CUSTOMER)
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping
	public List<CustomerDTO> findAll() {
		return customerService.findAll();
	}

	@PostMapping
	public void registerCustomer(@RequestBody CustomerDTO customerRequest) {
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
