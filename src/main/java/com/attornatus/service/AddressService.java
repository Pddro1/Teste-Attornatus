package com.attornatus.service;

import org.springframework.stereotype.Service;

import com.attornatus.model.Address;
import com.attornatus.model.Person;
import com.attornatus.repository.AddressRepository;
import com.attornatus.repository.PersonRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

	private AddressRepository addressRepository;
	private PersonRepository personRepository;
	
	

	@Transactional
	public Address saveAddress(Address address) {
		Person person = personRepository.findById(address.getPerson().getId()).orElse(null);
		
		if(person != null) {
			address.setPerson(person);
			person.getAddress().add(address);
			
			return addressRepository.save(address);
			
		}
		return null;
	}
}
