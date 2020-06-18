package com.jpa.database.jpadata.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jpa.database.jpadata.entity.Person;

@Repository
public class PersonJdbcDao {
	
	
	@Autowired
	JdbcTemplate jdbcTemp;
	
	// defined custom RowMapper so wer can use it inplace of new BeanPropertyRowMapper(Person.class) 
	class PersonRowMapper implements RowMapper<Person>{

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			
			return person;
		}
		
		
	}
	
	
	public List<Person> findAllData(){
		return jdbcTemp.query("select * from person",
				new PersonRowMapper());    // We can replace new BeanPropertyRowMapper(Person.class) to new PersonRowMapper()
	}

	public Person findIdPerson(int id) { // this is to find query for specific object
		return jdbcTemp.queryForObject("select * from person where id=?", 
				new Object[] { id },        //write query and map it here. mapping parameter
				new BeanPropertyRowMapper<Person>(Person.class) ); // mapping it back to person bean
	}
	
	
	public Person findNamePerson(String name, int id) {  // this is to find query for specific object
		return jdbcTemp.queryForObject("select * from person where name=? and id=?", 
				new Object[] { name, id }, 
				new BeanPropertyRowMapper<Person>(Person.class) );
	}
	
	
	public int deletNamePerson(String name) {  //  how many rows deleted
		return jdbcTemp.update("delete from person where name=?", 
				new Object[] { name }
				);
	}
	
	
	public int insertPerson(Person person) {  //  insert persons infomation
	return jdbcTemp.update(
			"insert into person (id, name, location, birth_date) " 
			+ "values(?,  ?, ?, ?)",
			new Object[] { 
					person.getId(), person.getName(), 
					person.getLocation(),
					new  Timestamp(person.getBirthDate().getTime()) }
			);
	}
	
	
	public int updatePerson(Person person) {  //  update persons infomation
		return jdbcTemp.update(
				"update person set name = ? , location = ? , birth_date = ? " 
				+ "where id = ? ",
				new Object[] { 
						person.getName(), 
						person.getLocation(),
						new  Timestamp(person.getBirthDate().getTime()), person.getId() }
				);
		}
	
	
	
	
	
}
