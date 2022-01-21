package com.iwlabs.customer.repository;

import com.iwlabs.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	Optional<Customer> findByUuid(String guid);
}
