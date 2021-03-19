package com.simplon.safety.alertsnet.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.safety.alertsnet.model.Person;
import com.simplon.safety.alertsnet.service.PersonService;


@RequestMapping("person")
@RestController
public class PersonController {
	
	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	
	@GetMapping
	public List<Person> getAllPeople() throws IOException {
		return personService.getAllPeople();
	}
	
	@PostMapping
	public void addPerson(@RequestBody Person person) {
		
		personService.addPerson(person);
	}
	

}
