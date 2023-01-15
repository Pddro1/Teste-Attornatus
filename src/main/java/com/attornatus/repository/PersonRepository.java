package com.attornatus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attornatus.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	List<Person> findAllByNameContainingIgnoreCase(String name);
}
