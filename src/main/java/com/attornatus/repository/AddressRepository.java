package com.attornatus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attornatus.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
	List<Address> findAllByCepContainingIgnoreCase(String cep);
}
