package com.simplon.safety.alertsnet.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.simplon.safety.alertsnet.AlertsnetApplication;
import com.simplon.safety.alertsnet.model.Person;


@Repository("PersonAccessDao")
public class PersonAccessService implements PersonDao{
	
	List<Person> listDePersons = new ArrayList<Person>();
	
	@Autowired
	ResourceLoader resourceLoader;
	
	public void dataInitilisation() throws IOException {
		
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any listPerson = obj.get("persons");

		for (Any ligne : listPerson) {
			
			this.listDePersons.add(new Person.PersonBuilder()
						                    .firstName(ligne.get("firstName").toString())
						                    .lastName(ligne.get("lastName").toString())
						                    .address(ligne.get("address").toString()) 
						                    .city(ligne.get("city").toString()) 
						                    .zip(ligne.get("zip").toString()) 
						                    .phone(ligne.get("phone").toString()) 
						                    .email(ligne.get("email").toString()) 
						                    .build()
						                    );					
		}
	}
	
	@Override
	public List<Person> selectAllPeople() throws IOException {
		
		if (AlertsnetApplication.personsData.isEmpty()) {
			
			this.dataInitilisation();
			AlertsnetApplication.personsData = this.listDePersons;

		}
		
		return this.listDePersons;
	}

	@Override
	public int insertPerson(Person person) throws IOException {
		
		if (AlertsnetApplication.personsData.isEmpty()) {
			
			this.dataInitilisation();
			this.listDePersons.add(person);
			AlertsnetApplication.personsData = this.listDePersons;
			
			return 1;
		} else {
			
			AlertsnetApplication.personsData.add(person);
			
			return 1;
		}
	}

	@Override
	public int deletePerson(Person person) throws IOException {
		
		if (AlertsnetApplication.personsData.isEmpty()) {
			
			this.dataInitilisation();
			AlertsnetApplication.personsData = this.listDePersons;
			
		}
		
		AlertsnetApplication.personsData.removeIf(personInList -> (   
				person.getFirstName().equals(personInList.getFirstName()) 
				&& person.getLastName().equals(personInList.getLastName())));
	
		return 1;
	}

	@Override
	public int updatePerson(Person oldPerson, Person newPerson) throws IOException {
		
		if (AlertsnetApplication.personsData.isEmpty()) {
			
			this.dataInitilisation();
			AlertsnetApplication.personsData = this.listDePersons;
			
		}
		
		this.listDePersons = AlertsnetApplication.personsData;
		
		int indice = findIndiceDePerson(oldPerson);
		
		AlertsnetApplication.personsData.set(indice, newPerson);
		
		return 1;
	}

	public int findIndiceDePerson(Person oldPerson) {
		int counter = 0;
		
		for (Person person : listDePersons ) {
			if (person.getFirstName().equals(oldPerson.getFirstName()) &&
				person.getLastName().equals(oldPerson.getLastName()) ) {
				
				return counter;
			}

			counter++;			
		}
		
		return -1;
	}
	
	public String readJsonFile() throws IOException { 
		String persons = "";
		try {
		    persons = new String(
		      Files.readAllBytes(new File("src/main/resources/data.json").toPath()));
		}
		catch (Exception e) {
			System.out.println("errrrrrrrrrrrror :" + e);
		}
	   
	    return persons;
	}
	
}
