package com.simplon.safety.alertsnet.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.simplon.safety.alertsnet.model.Person;


public interface PersonDao {
	
    int insertPerson(Person person);
	
	List<Person> selectAllPeople() throws IOException;
	
	int deletePerson(Person person);
	
	int updatePersoById(Person person, Person newPerson);
	
	Optional<Person> selectPersonByFirstLastName(String lastName, String firstName);
	

}
