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

import ch.qos.logback.classic.Logger;

//@SpringBootApplication   // comment here becase we dont wnat to confuse when we run JpaApplication
public class SpringJdbcApplication implements CommandLineRunner {

	private Logger loger = (Logger) LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	PersonJdbcDao daoPerson;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	      
		loger.info("All users --> {}",
				daoPerson.findAllData());
		
		loger.info("Use id 10001 is -->{} " , 
				daoPerson.findIdPerson(10001));
		
		loger.info("Use name Ehsan and Id 10001 is -->{} " ,
				 daoPerson.findNamePerson("Ehsan", 10001));
		
		loger.info("Delet Majed --> num of rows deleted -->{}",
				daoPerson.deletNamePerson("Majed"));
		
		loger.info("Inseting 10007 -->{}", 
				daoPerson.insertPerson(new Person(10007,"Meher","VA", new Date())) );
		
		loger.info("Update Id 10003 -- {}", 
				daoPerson.updatePerson(new Person(1003,"Hadi", "Walnut Creek", new Date() )));
	}

}
