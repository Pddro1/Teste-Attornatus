package com.attornatus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.service.AddressService;
import com.attornatus.model.Address;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/save-address")
@RequiredArgsConstructor
public class AddressController {

	private  AddressService addressService;
	
	@PostMapping
	public ResponseEntity<Address> createAddress(@RequestBody Address address){
		Address saveAddress = addressService.saveAddress(address);
		
		if(saveAddress != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(saveAddress);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
}
