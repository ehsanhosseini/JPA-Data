package com.jpa.database.jpadata.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.database.jpadata.entity.Person;

public  interface PersonSpringDataRepository extends JpaRepository<Person, Integer>{

	
}
