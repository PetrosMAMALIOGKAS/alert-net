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
import com.simplon.safety.alertsnet.model.MedicalRecord;
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
	
	@Autowired 
	MedicalRecordDao medicalRecordDao;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void dataInitilisation() throws IOException
	{
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any listPerson = obj.get("persons");
		
		for (Any ligne : listPerson) {
			
		//	long IdFirestationResponsable = firestationDao.getIdFirestationResponsable_withAddress(ligne.get("address").toString());
		//	Firestation firestationObject_ofAddressToInsert = entityManager.getReference(Firestation.class, IdFirestationResponsable);
			
			long addressId = addressDao.insertAddress(new Address.AddressBuilder()
								               .rue_name_number(ligne.get("address").toString()) 
						                       .city(ligne.get("city").toString()) 
						 		               .zip(ligne.get("zip").toString())
						 		             //  .firestation_responsable(firestationObject_ofAddressToInsert)
								               .build());
			
			Address addressObject_ofPersonToInsert = entityManager.getReference(Address.class, addressId);
			
			String personFirstName = ligne.get("firstName").toString();
			String personLastName  = ligne.get("lastName").toString();
			
		//	long idOfPerson_toInsert = medicalRecordDao.getMedicalRecordId_ByFirstLastName(personFirstName, personLastName );
		//	MedicalRecord medicalRecordObject_ofPersonToInsert = entityManager.getReference(MedicalRecord.class, idOfPerson_toInsert);
			
			this.listDePersons.add(new Person.PersonBuilder()
						                    .firstName(personFirstName)
						                    .lastName(personLastName)
						                    .person_address(addressObject_ofPersonToInsert)
						              //      .medicalRecord(medicalRecordObject_ofPersonToInsert)
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
		
		//medicalRecordDao.initMediacalRecordTable();
		
		
		
		this.dataInitilisation();
		
		for (Person person: this.listDePersons ) {
			
			//personRepository.save(person);
			this.insertPerson(person);
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
		
	    person.setPerson_address(address_of_person_to_insert);

		Person insertResult = personRepository.save(person);
		if  (insertResult.equals(person)) {
	      return 1;
		}
		  
		return -1;
	}
	
	@Override
	public int insertPerson(Person person) throws IOException
	{
		
		MedicalRecord personMediacalRecord = medicalRecordDao.insertMedicalRecord(person);
		
		System.out.println(personMediacalRecord.toString());
		
		Person insertResult = personRepository.save(person);
		if  (insertResult.equals(person)) {
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
