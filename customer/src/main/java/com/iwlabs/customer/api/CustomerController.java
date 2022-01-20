package com.iwlabs.customer.api;


import com.iwlabs.customer.domain.dto.CustomerDTO;
import com.iwlabs.customer.service.CustomerService;
import com.iwlabs.customer.utils.Endpoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.CUSTOMER)
@Slf4j
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping
	public void registerCustomer(@RequestBody CustomerDTO customerRequest) {
		log.info("New customer registration {}",customerRequest);
		customerService.registerCustomer(customerRequest);
	}

	@GetMapping("/{id}")
	public CustomerDTO getById(@PathVariable Integer id) {
		return customerService.getById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		customerService.deleteCustomer(id);
	}

	@PutMapping("/{id}")
	public CustomerDTO updateCustomer(@PathVariable Integer id,@RequestBody CustomerDTO customerDTO) {
		return customerService.updateCustomer(id,customerDTO);
	}


}
