package com.simplon.safety.alertsnet.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.simplon.safety.alertsnet.model.Person;


public interface PersonDao {
	
	List<Person> initPersonTable() throws IOException; 
	
    int insertPerson(Person person) throws IOException;
	
	List<Person> selectAllPeople() throws IOException;
	
	Optional<Person> getPersonById(Long id);
	
	int deletePerson(Person person) throws IOException;
	
	int deletePersonById(Long id);
	
	int updatePersonById(Long id, Person newPerson);
	
}


