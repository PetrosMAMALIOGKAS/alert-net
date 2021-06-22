package com.simplon.safety.alertsnet.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.simplon.safety.alertsnet.AlertsnetApplication;
import com.simplon.safety.alertsnet.exceptions.ResourceNotFoundException;
import com.simplon.safety.alertsnet.model.Address;
import com.simplon.safety.alertsnet.model.Firestation;
import com.simplon.safety.alertsnet.model.Person;


@Repository("PersonAccessDao")
public class PersonAccessService implements PersonDao{
	
	List<Person> listDePersons = new ArrayList<Person>();
	
	@Autowired                 
    private PersonRepository personRepository;
	
	@Autowired 
	AddressDao addressDao;
	
	@Autowired 
	FirestationDao firestationDao;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void dataInitilisation() throws IOException
	{
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any listPerson = obj.get("persons");
		
		for (Any ligne : listPerson) {
			
			long IdFirestationResponsable = firestationDao.getIdFirestationResponsable_withAddress(ligne.get("address").toString());
			Firestation firestationObject_ofAddressToInsert = entityManager.getReference(Firestation.class, IdFirestationResponsable);
			
			long addressId = addressDao.insertAddress(new Address.AddressBuilder()
								               .rue_name_number(ligne.get("address").toString()) 
						                       .city(ligne.get("city").toString()) 
						 		               .zip(ligne.get("zip").toString())
						 		               .firestation_responsable(firestationObject_ofAddressToInsert)
								               .build());
			
			Address addressObject_ofPersonToInsert = entityManager.getReference(Address.class, addressId);
			
			this.listDePersons.add(new Person.PersonBuilder()
						                    .firstName(ligne.get("firstName").toString())
						                    .lastName(ligne.get("lastName").toString())
						                    .person_address(addressObject_ofPersonToInsert)
						                    .phone(ligne.get("phone").toString()) 
						                    .email(ligne.get("email").toString()) 
						                    .build()
						                    );
		}
	}
	
	@Override
	public List<Person> selectAllPeople() throws IOException 
	{
		return (List<Person>) personRepository.findAll();
	}
	
	@Override
	public int initPersonTable() throws IOException 
	{
		
		firestationDao.initFirestationsTable();
		
		addressDao.initAddressTable();
		
		
		
		this.dataInitilisation();
		
		for (Person person: this.listDePersons ) {
			
			personRepository.save(person);
		}
		
		return 1;
	}
	
	@Override
	public Optional<Person> getPersonById(Long id) 
	{
		return personRepository.findById(id);
	}

	@Override
	public int insertPerson(Person person, Address address) throws IOException
	{
		long addressId = addressDao.insertAddress(address);
		Address address_of_person_to_insert = entityManager.getReference(Address.class, addressId);
		
		Person p = new Person();
		
		p.setFirstName(person.getFirstName());
		p.setLastName(person.getLastName());
	    p.setPerson_address(address_of_person_to_insert);
		p.setPhone(person.getPhone());
		p.setEmail(person.getEmail());
		  
		Person insertResult = personRepository.save(p);
		if  (insertResult.equals(p)) {
	      return 1;
		}
		  
		return -1;
	}
	
	@Override
	public int deletePersonById(Long  id) 
	{
		personRepository.deleteById(id);
		return 1;
	}
	
	@Override
	public int updatePersonById(Long id, Person newPerson) 
	{
		Optional<Person> personDb = this.personRepository.findById(id);

		if (personDb.isPresent()) {
			
			Person personUpdate = personDb.get();
		
//			if ( ! personUpdate.getAddress().equals(newPerson.getAddress())) {
//				
//				personRepository.updatePersonAddress(newPerson.getAddress(), id);
//			}
//			
//			if ( ! personUpdate.getZip().equals(newPerson.getZip())) {
//				
//				personRepository.updatePersonZip(newPerson.getZip(), id);
//			}
			
			if ( ! personUpdate.getPhone().equals(newPerson.getPhone())) {
				
				personRepository.updatePersonPhone(newPerson.getPhone(), id);
			}
				
			if ( ! personUpdate.getEmail().equals(newPerson.getEmail())) {
				
				personRepository.updatePersonEmail(newPerson.getEmail(), id);
			}
			
//			if ( ! personUpdate.getCity().equals(newPerson.getCity())) {
//				
//				personRepository.updatePersonCity(newPerson.getCity(), id);
//			}
			
			return 1;
		} else {
			
			throw new ResourceNotFoundException("Person not found with id : " + id);
		}
	}

	@Override
	public int deletePerson(Person person) throws IOException 
	{
		if (AlertsnetApplication.personsData.isEmpty()) {
			
			this.dataInitilisation();
			AlertsnetApplication.personsData = this.listDePersons;
			
		}
		
		AlertsnetApplication.personsData.removeIf(personInList -> (   
				person.getFirstName().equals(personInList.getFirstName()) 
				&& person.getLastName().equals(personInList.getLastName())));
	
		return 1;
	}
	
	public String readJsonFile() throws IOException
	{ 
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
