package com.simplon.safety.alertsnet.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.simplon.safety.alertsnet.model.Person;


@Repository("PersonAccessDao")
public class PersonAccessService implements PersonDao{
	
	List<Person> listDePersons = new ArrayList<Person>();
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Override
	public int insertPerson(Person person) {
		System.out.println(person.toString());
		return 0;
	}

	@Override
	public List<Person> selectAllPeople() throws IOException {
		
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any listPerson = obj.get("persons");

		for (Any ligne : listPerson) {
			listDePersons.add(new Person.Builder()
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
		
		return listDePersons;
	}

	@Override
	public int deletePerson(Person person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePersoById(Person person, Person newPerson) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<Person> selectPersonByFirstLastName(String lastName, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Resource loadPersons() {
		return (Resource) resourceLoader.getResource(
			      "classpath:src/main/resources/data.json");
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
