package com.jpa.database.jpadata.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity

// we are using namedquery to get values from database and use JPQA to write the query
@NamedQuery(name="find_all_persons", query="select p from Person p" )  // defind namequery to get all persons, Here we query Person(it is refering to the entity not table) 
public class Person {
	
	
	@Id  // Id is parimary key so we @Id
	@GeneratedValue
	private int id;
	
	
	private String name;
	private String location;
	private Date birthDate;
	public Person(int id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	
	public Person(String name, String location, Date birthDate) { // creating another constructor to not use id becase hibernate Jap will create automatically for us
		super();
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	
	
	public Person() {    // By default java provide this constructor but here we need to initiolize in this case bacuse we provide constructor java nolonger provide default constructor 
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return String.format("Person [id=%s, name=%s, location=%s, birthDate=%s]", id, name, location, birthDate);
	}
	
	
	

}
