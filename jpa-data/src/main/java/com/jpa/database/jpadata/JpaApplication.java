package com.jpa.database.jpadata;


import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jpa.database.jpadata.entity.Person;
import com.jpa.database.jpadata.jdbc.PersonJdbcDao;
import com.jpa.database.jpadata.jpa.PersonJpaRepository;

import ch.qos.logback.classic.Logger;

//@SpringBootApplication  // Comment this bz I wAant to run SpringDataApplication
public class JpaApplication implements CommandLineRunner {

	private Logger loger = (Logger) LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	PersonJpaRepository reposetory;  
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	      
		loger.info("All users --> {}",
				reposetory.findAllData());
		
		loger.info("Use id 10001 is -->{} " , 
				reposetory.findIdPerson(10001));
		
		reposetory.deletById(10002);   // not using logger because we use void
		
		loger.info("Inseting -->{}", 
				reposetory.insertPerson(new Person("Meher","VA", new Date())));  // Here we are not passing Id becasue Hibernate will create it authomatically
		
		loger.info("Update Id 10003 -- {}", 
				reposetory.updatePerson(new Person(1003,"Hadi", "Walnut Creek", new Date() )));
	}

}
