package com.simplon.safety.alertsnet.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.simplon.safety.alertsnet.model.Address;
import com.simplon.safety.alertsnet.model.Person;


public interface PersonDao {
	
	int initPersonTable() throws IOException; 
	
    int insertPerson(Person person, Address address) throws IOException;
	
	List<Person> selectAllPeople() throws IOException;
	
	Optional<Person> getPersonById(Long id);
	
	int deletePerson(Person person) throws IOException;
	
	int deletePersonById(Long id);
	
	int updatePersonById(Long id, Person newPerson);
	
}


