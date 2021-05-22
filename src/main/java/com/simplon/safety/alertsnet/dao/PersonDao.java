package com.simplon.safety.alertsnet.dao;

import java.io.IOException;
import java.util.List;

import com.simplon.safety.alertsnet.model.Person;


public interface PersonDao {
	
    int insertPerson(Person person) throws IOException;
	
	List<Person> selectAllPeople() throws IOException;
	
	int deletePerson(Person person) throws IOException;
	
	int updatePerson(Person person, Person newPerson) throws IOException;
	
}


