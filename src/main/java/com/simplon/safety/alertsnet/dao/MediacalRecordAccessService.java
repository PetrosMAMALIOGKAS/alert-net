package com.simplon.safety.alertsnet.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.simplon.safety.alertsnet.AlertsnetApplication;
import com.simplon.safety.alertsnet.model.MedicalRecord;
import com.simplon.safety.alertsnet.model.Person;


@Repository("MediacalRecordAccessDao")
public class MediacalRecordAccessService implements MedicalRecordDao{
	
	List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
	
	
	public void dataInitilisation() throws IOException {
		
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any data = obj.get("medicalrecords");
		
		for (Any ligne : data) {
			List<String> listMedications = new ArrayList<String>();
			List<String> listAllergies = new ArrayList<String>();
			
			if (ligne.get("medications").size() > 0) {

				for (Any medication : ligne.get("medications")) {
					
					listMedications.add(medication.toString());
				}
			}
			
			if (ligne.get("allergies").size() > 0) {
				
				for (Any allergie : ligne.get("allergies")) {
					listAllergies.add(allergie.toString());
				}
			}

			this.listMedicalRecords.add(
					new MedicalRecord.MedicalRecordBuilder()
				                     .firstName(ligne.get("firstName").toString())
				                     .lastName(ligne.get("lastName").toString())
				                     .birthdate(ligne.get("birthdate").toString())
				                     .medications(listMedications) 
				                     .allergies(listAllergies) 
				                     .build()
				                     );
		} 
		
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
	public int insertMediacalRecord(MedicalRecord medicalRecord) throws IOException {
		if (AlertsnetApplication.medicalRecordsData.isEmpty()) {
			
			this.dataInitilisation();
			this.listMedicalRecords.add(medicalRecord);
			AlertsnetApplication.medicalRecordsData = this.listMedicalRecords;
			
			return 1;
		} else {
			
			AlertsnetApplication.medicalRecordsData.add(medicalRecord);
			
			return 1;
		}
	}

	

	@Override
	public int deleteMedicalRecord(MedicalRecord medicalRecord) throws IOException {
		
		if (AlertsnetApplication.medicalRecordsData.isEmpty()) {
			
			this.dataInitilisation();
			AlertsnetApplication.medicalRecordsData = this.listMedicalRecords;
			
		}
		
		AlertsnetApplication.medicalRecordsData.removeIf(medicalInList -> (   
				medicalRecord.getFirstName().equals(medicalInList.getFirstName()) 
				&& medicalRecord.getLastName().equals(medicalInList.getLastName())));
	
		return 1;
	}

	
	
	
	@Override
	public int updateMedicalRecord(MedicalRecord oldMedicalRecord, MedicalRecord newMedicalRecord) throws IOException {
			
			if (AlertsnetApplication.medicalRecordsData.isEmpty()) {
				
				this.dataInitilisation();
				AlertsnetApplication.medicalRecordsData = this.listMedicalRecords;
				
			}
			
			this.listMedicalRecords = AlertsnetApplication.medicalRecordsData;
			
			int indice = findIndiceDeMedicalRecord(oldMedicalRecord);
			
			AlertsnetApplication.medicalRecordsData.set(indice, newMedicalRecord);
			
			return 1;	
		
	}
	
	public int findIndiceDeMedicalRecord(MedicalRecord oldMedicalRecord) {
		int counter = 0;
		
		for (MedicalRecord medicalRecord : listMedicalRecords ) {
			if (medicalRecord.getFirstName().equals(oldMedicalRecord.getFirstName()) &&
				medicalRecord.getLastName().equals(oldMedicalRecord.getLastName()) ) {
				
				return counter;
			}

			counter++;			
		}
		
		return -1;
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
