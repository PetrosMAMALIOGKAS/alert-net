package com.simplon.safety.alertsnet.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.safety.alertsnet.dao.PersonRepository;
import com.simplon.safety.alertsnet.model.Person;
import com.simplon.safety.alertsnet.service.PersonService;


@RequestMapping(path = "/person")
@RestController
public class PersonController {
	
	private final PersonService personService;
	
    @Autowired                 
    private PersonRepository personRepository;
	  
    @Autowired
	public PersonController(PersonService personService) {
		
      this.personService = personService;
	}
    
	@PostMapping(path="/init")
	public @ResponseBody String initialisationOfPersonData () throws IOException {
		  
	  List<Person> personTableContent = new ArrayList<Person>();
	  personTableContent = personService.initPersonTable();
		
	  personRepository.saveAll(personTableContent);
    
	  return "table Person Initialised";
	}
  
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Person> getAllPersons() throws IOException {
		
		return personService.getAllPeople();
	}
	  
	  
	@GetMapping("/{id}")
	public @ResponseBody Optional<Person> getPersonById(@PathVariable long id) {
			
		return personService.getPersonById(id);
	}

	@PostMapping(path="/add")
	public @ResponseBody String addNewPerson(@RequestBody Person person) throws IOException {
	
	  int result = personService.addPerson(person);
	  if (result == -1) {
		  return "Not Saved: there was an error";
	  }
	  return "Saved";
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody String deletePersonById(@PathVariable long id) throws IOException {
		
		personService.deletePersonById(id);
		return "Person with id:" + id + " deleted";
	}
	
	@PutMapping("/{id}")
	public void updatePersonById(@PathVariable long id,  @RequestBody Person newPerson )
	{
		personService.updatePersonById(id, newPerson);
	}
	
	  

	
	



	/*************************************************************
	 * version 1.0
	@Autowired
	public PersonController(PersonService personService) {
		
		this.personService = personService;
	}
	
	
	@GetMapping
	public List<Person> getAllPeople() throws IOException {
		
		return personService.getAllPeople();
	}
	
	@PostMapping
	public void addPerson(@RequestBody Person person) throws IOException {
		
		personService.addPerson(person);
	}
	
	@DeleteMapping
	public void deletePerson(@RequestBody Person person) throws IOException {
		
		personService.deletePerson(person);
	}
	
	@PutMapping
	public void updatePerson(@RequestParam("lastName") String lastName,
			                 @RequestParam("firstName") String firstName,
			                 @RequestBody Person newPerson   )  throws IOException {
		
		Person oldPerson = new Person.PersonBuilder()
				                     .lastName(lastName)
				                     .firstName(firstName)
				                     .build();
		
		personService.updatePerson(oldPerson, newPerson);
	}
	
	*******************************************************************/

}
