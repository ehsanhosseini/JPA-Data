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
import com.jpa.database.jpadata.springdata.PersonSpringDataRepository;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Logger loger = (Logger) LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	PersonSpringDataRepository reposetory;  
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	      
		loger.info("All users --> {}",
				reposetory.findAll());
		
		loger.info("Use id 10001 is -->{} " , 
				reposetory.findById(10001));
		
		reposetory.deleteById(10002);   // not using logger because we use void
		
		loger.info("Inseting -->{}", 
				reposetory.save(new Person("Meher","VA", new Date())));  // Here we are not passing Id becasue Hibernate will create it authomatically
		
		loger.info("Update Id 10003 -- {}", 
				reposetory.save(new Person(1003,"Hadi", "Walnut Creek", new Date() )));
	}

}
