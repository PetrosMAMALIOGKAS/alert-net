package com.simplon.safety.alertsnet.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.simplon.safety.alertsnet.AlertsnetApplication;
import com.simplon.safety.alertsnet.model.Allergie;
import com.simplon.safety.alertsnet.model.MedicalRecord;
import com.simplon.safety.alertsnet.model.Medication;
import com.simplon.safety.alertsnet.model.Person;


@Repository("MediacalRecordAccessDao")
public class MediacalRecordAccessService implements MedicalRecordDao{
	
	List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
	
	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	
	
	public void dataInitilisation() throws IOException {
		
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any data = obj.get("medicalrecords");
		
		for (Any ligne : data) {
//			List<String> listMedications = new ArrayList<String>();
//			List<String> listAllergies = new ArrayList<String>();
			
//			if (ligne.get("medications").size() > 0) {
//
//				for (Any medication : ligne.get("medications")) {
//					
//					listMedications.add(medication.toString());
//				}
//			}
			
//			if (ligne.get("allergies").size() > 0) {
//				
//				for (Any allergie : ligne.get("allergies")) {
//					listAllergies.add(allergie.toString());
//				}
//			}

			this.listMedicalRecords.add(
					new MedicalRecord.MedicalRecordBuilder()
				                     .birthdate(ligne.get("birthdate").toString())
//				                     .medications(listMedications) 
//				                     .allergies(listAllergies) 
				                     .build()
				                     );
		} 
	}
	
	@Override
	public long initMediacalRecordTable() throws IOException 
	{
		this.dataInitilisation();
		
		for (MedicalRecord medicalRecord : this.listMedicalRecords) {
			
			this.insertMedicalRecord(medicalRecord);
		}	
		return 1;
	}
	
	
	@Override
	public List<MedicalRecord> listAllMedicalRecords() throws IOException {
		
		if (AlertsnetApplication.medicalRecordsData.isEmpty()) {
			
			this.dataInitilisation();
			AlertsnetApplication.medicalRecordsData = this.listMedicalRecords;
			
		}

		return listMedicalRecords;
	}
	
	
	
	@Override
	public int insertMedicalRecord(MedicalRecord medicalRecord) throws IOException {
		
//		if (AlertsnetApplication.medicalRecordsData.isEmpty()) {
//			
//			this.dataInitilisation();
//			this.listMedicalRecords.add(medicalRecord);
//			AlertsnetApplication.medicalRecordsData = this.listMedicalRecords;
//			
//			return 1;
//		} else {
//			
//			AlertsnetApplication.medicalRecordsData.add(medicalRecord);
//			
//			return 1;
//		}
		medicalRecordRepository.save(medicalRecord);
		
		return 1;
	}
	

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public MedicalRecord insertMedicalRecord(Person person) throws IOException {
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any data = obj.get("medicalrecords");

		for (Any ligne : data) {

			String firstNameActual = ligne.get("firstName").toString();
			String lastNameActual = ligne.get("lastName").toString();
			
			if  (firstNameActual.equals(person.getFirstName()) && lastNameActual.equals(person.getLastName()) ) {
				
				Any listMedicationsIntheJSONfile  = ligne.get("medications");
				Set<Medication> listMedicationToInsertToDb = new HashSet<>();
				if (listMedicationsIntheJSONfile.size() > 0) {
					for (Any med : listMedicationsIntheJSONfile) {
						listMedicationToInsertToDb.add(new Medication.MedicationBuilder()
								                                     .designation(med.toString())
								                                     .build());
					}
				}
				
				Any listAllergiesIntheJSONfile  = ligne.get("allergies");
				Set<Allergie> listAllergiesToInsertToDb = new HashSet<>();
				if (listAllergiesIntheJSONfile.size() > 0) {
					for (Any allergie : listAllergiesIntheJSONfile) {
						listAllergiesToInsertToDb.add(new Allergie.AllergieBuilder()
								                                     .designation(allergie.toString())
								                                     .build());
					}
				}
				
				MedicalRecord mediacalRecordToInsert = new MedicalRecord.MedicalRecordBuilder()
						                                                .birthdate(ligne.get("birthdate").toString())
						                                                .medications(listMedicationToInsertToDb)
						                                                .allergies(listAllergiesToInsertToDb)
						                                                .build();
						                                                
			    MedicalRecord medicalRecordSaved =  medicalRecordRepository.save(mediacalRecordToInsert);
			    return medicalRecordSaved;
			}
		}
		return null;
	}

	

	@Override
	public int deleteMedicalRecord(MedicalRecord medicalRecord) throws IOException {
		
		if (AlertsnetApplication.medicalRecordsData.isEmpty()) {
			
			this.dataInitilisation();
			AlertsnetApplication.medicalRecordsData = this.listMedicalRecords;
			
		}
		
//		AlertsnetApplication.medicalRecordsData.removeIf(medicalInList -> (   
//				medicalRecord.getFirstName().equals(medicalInList.getFirstName()) 
//				&& medicalRecord.getLastName().equals(medicalInList.getLastName())));
	
		return 1;
	}

	@Override
	public int updateMedicalRecord(MedicalRecord oldMedicalRecord, MedicalRecord newMedicalRecord) throws IOException {
			
			if (AlertsnetApplication.medicalRecordsData.isEmpty()) {
				
				this.dataInitilisation();
				AlertsnetApplication.medicalRecordsData = this.listMedicalRecords;
				
			}
			
			this.listMedicalRecords = AlertsnetApplication.medicalRecordsData;
			
//			int indice = findIndiceDeMedicalRecord(oldMedicalRecord);
//			
//			AlertsnetApplication.medicalRecordsData.set(indice, newMedicalRecord);
			
			return 1;	
		
	}
	
	@Override
	public long getMedicalRecordId_ByFirstLastName(String personFirstName, String personLastName) throws IOException 
	{
		
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any data = obj.get("medicalrecords");
		
		String birthdate = "";
		
		for (Any ligne : data) { 
			
			String firstName = ligne.get("firstName").toString();
			String lastName = ligne.get("lastName").toString();
			
			if (firstName.equals(personFirstName) && lastName.equals(personLastName)) {
				birthdate = ligne.get("birthdate").toString();
			}
		}
		
		return medicalRecordRepository.getId_byDate(birthdate);
	}
	
	
	public String readJsonFile() throws IOException { 
		String data = "";
		try {
		    data = new String(
		      Files.readAllBytes(new File("src/main/resources/data.json").toPath()));
		}
		catch (Exception e) {
			System.out.println("errrrrrrrrrrrror :" + e);
		}
	   
	    return data;
	}


}