package com.attornatus.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.model.Address;
import com.attornatus.model.Person;
import com.attornatus.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

	private PersonService service;
	
	@PostMapping
	public ResponseEntity<Person> save(@RequestBody Person person) {
		service.save(person);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
	
	@GetMapping
	public ResponseEntity<List<Person>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Person>> findByName(@PathVariable("name") String name){
		return ResponseEntity.ok(service.findByName(name));
	}
	
	@GetMapping("/{id}/address-list")
	public ResponseEntity<List<Address>> listAddress(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.addressList(id));
	}
	
	@GetMapping("/{id}/principal-address")
	public ResponseEntity<Address> principalAddress(@PathVariable Long id) {
		var principalAddress = service.findPrincipalAddress(id);
			return ResponseEntity.ok(principalAddress);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person){
		Person updatePerson = service.update(person);
		
		if(updatePerson != null) {
			return ResponseEntity.ok(updatePerson);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
