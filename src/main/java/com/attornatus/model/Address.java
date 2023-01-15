package com.attornatus.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private	String publicArea;
	private	String number;
	private	String cep;
	private	String city;
	private Boolean principalAddress;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("address")
	@JoinColumn(name = "person_id")
	@JsonBackReference
	private Person person;
	
	public boolean isPrincipal() {
		return principalAddress;
	}
	
	public Person getPerson(){
		return this.person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
}
