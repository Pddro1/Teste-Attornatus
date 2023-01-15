package com.attornatus.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.attornatus.model.Address;
import com.attornatus.model.Person;
import com.attornatus.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

	private PersonRepository personRepository;
	
	@Transactional
	public Person save(Person person) {
		return personRepository.save(person);
	}
	
	public List<Person> findAll(){
		List<Person> people = personRepository.findAll();
			people.forEach(p -> Hibernate.initialize(p.getAddress()));
				return people;
	}
	
	@Transactional
	public Person update(Person person) {
		if(personRepository.existsById(person.getId())) {
			return personRepository.save(person);
		}
		throw new EntityNotFoundException("Person not found");
	}
	
	public List<Address> addressList(Long id){
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Person not found"));
		
		return person.getAddress();
	}
	
	public List<Person> findByName(String name){
		return personRepository.findAllByNameContainingIgnoreCase(name);
	}
	
	public Address findPrincipalAddress(Long id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException
						("Person " + id + " not found"));
		return person.getAddress().stream()
				.filter(Address::isPrincipal)
				.findFirst().orElseThrow(() -> new EntityNotFoundException
						("Person's" + id + "primary address not found"));
	}
	
}
