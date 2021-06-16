package com.simplon.safety.alertsnet.api;

import java.io.IOException;

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


import com.simplon.safety.alertsnet.model.Person;
import com.simplon.safety.alertsnet.service.AddressService;
import com.simplon.safety.alertsnet.service.PersonService;


@RequestMapping(path = "/person")
@RestController
public class PersonController {
	
	private final PersonService personService;
	private final AddressService addressService;
	
	  
    @Autowired
	public PersonController(PersonService personService, AddressService addressService)
    {
      this.personService = personService;
      this.addressService = addressService;
	}
    
	@PostMapping(path="/init")
	public @ResponseBody String initialisationOfPersonData () throws IOException
	{
	  int resultPerson;
	  int resultAddress;

	  resultPerson = personService.initPersonTable();
	  resultAddress = addressService.initAddressTable();
		
	  //personRepository.saveAll(personTableContent);
	  if (resultPerson == 1 && resultAddress == 1) {
		  return "table Person et address Initialised";
	  }
	  
	  return "ERROR -----table Person not Initialised";
	}
  
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Person> getAllPersons() throws IOException {
		
		return personService.getAllPeople();
	}
	  
	  
	@GetMapping("/{id}")
	public @ResponseBody Optional<Person> getPersonById(@PathVariable long id) 
	{
		return personService.getPersonById(id);
	}

	@PostMapping(path="/add")
	public @ResponseBody String addNewPerson(@RequestBody Person person) throws IOException 
	{
	  int result = personService.addPerson(person);
	  if (result == -1) {
		  return "Not Saved: there was an error";
	  }
	  return "Saved";
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody String deletePersonById(@PathVariable long id) throws IOException
	{
		personService.deletePersonById(id);
		return "Person with id:" + id + " deleted";
	}
	
	@PutMapping("/{id}")
	public void updatePersonById(@PathVariable long id,  @RequestBody Person newPerson )
	{
		personService.updatePersonById(id, newPerson);
	}

}
