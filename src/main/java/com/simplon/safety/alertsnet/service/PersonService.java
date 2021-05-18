package com.simplon.safety.alertsnet.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simplon.safety.alertsnet.dao.PersonDao;
import com.simplon.safety.alertsnet.model.Person;


@Service
public class PersonService {
	
	private final PersonDao personDao;
	
	@Autowired
	public PersonService(@Qualifier("PersonAccessDao") PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public List<Person> getAllPeople() throws IOException {
		return personDao.selectAllPeople();
	}
	
	public int addPerson(Person person) throws IOException {
		return personDao.insertPerson(person);
	}
	
	public int deletePerson(Person person) throws IOException {
		return personDao.deletePerson(person);
	}
	
	public int updatePerson(Person person, Person newPerson) throws IOException {
		return personDao.updatePerson(person, newPerson);
	}

	

}
