package com.jpa.database.jpadata.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;


import com.jpa.database.jpadata.entity.Person;



@Repository
@Transactional
public class PersonJpaRepository {

	//connect to the database 
	
	@PersistenceContext
	EntityManager entitryManager;
	
	public List<Person> findAllData(){
		 TypedQuery<Person> namedQuery =  entitryManager.createNamedQuery("find_all_persons", Person.class); // Give a quesry a name(find_all_persons) and want entity return(Pesron.class)
		 return namedQuery.getResultList();
	}
	
	
	public Person findIdPerson(int id) {
		return  entitryManager.find(Person.class, id);  // entity is Person.class and Primry key is id
	}
	
	
	public Person updatePerson(Person person) {  // for update we call mereg method. check if id set inside the person. if there is id in person then it will try to update that person
		return entitryManager.merge(person);
	} 
	
	
	public Person insertPerson(Person person) {  
		return entitryManager.merge(person);
	} 
	
	
	public void deletById(int id) {
		Person person = findIdPerson(id);  // In Hibernate before deleting the person we need to get person out.
		entitryManager.remove(person);
	}
	
	
	
	
}
