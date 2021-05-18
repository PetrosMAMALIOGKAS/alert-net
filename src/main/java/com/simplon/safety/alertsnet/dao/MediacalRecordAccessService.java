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


@Repository("MediacalRecordAccessDao")
public class MediacalRecordAccessService implements MedicalRecordDao{
	
	List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
	
	
	public void dataInitilisation() throws IOException {
		
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any data = obj.get("medicalrecords");
		
		List<String> listMedications = new ArrayList<String>();
		List<String> listAllergies = new ArrayList<String>();
		
		//int counter = 0;
		for (Any ligne : data) {
			
			if (ligne.get("medications").size() > 0) {

				for (Any medication : ligne.get("medications")) {
					
					listMedications.add(medication.toString());
				}
				//System.out.println(listMedications.size() + "%%%%%%%%%%%%");
			}
			
			if (ligne.get("allergies").size() > 0) {
				
				for (Any allergie : ligne.get("allergies")) {
					listAllergies.add(allergie.toString());
				}
				
			}
			//System.out.println(listMedications.get(1) + "######");

			this.listMedicalRecords.add(new MedicalRecord.MedicalRecordBuilder()
						                    .firstName(ligne.get("firstName").toString())
						                    .lastName(ligne.get("lastName").toString())
						                    .birthdate(ligne.get("birthdate").toString())
						                    .medications(listMedications) 
						                    .allergies(listAllergies) 
						                    .build()
						                    );
			
			//System.out.println(listMedicalRecords.get(0).toString());
			//System.out.println(listMedicalRecords.get(counter).medications.get(1) + "****");
			//System.out.println(listMedicalRecords.size() + "   " + listMedications.size()  + " ~~~~");
			listMedications.clear();
			listAllergies.clear();
		
		}
		
	}
	
	@Override
	public List<MedicalRecord> listAllMedicalRecords() throws IOException {
		
//		if (AlertsnetApplication.medicalRecordsData.isEmpty()) {
//			
//			this.dataInitilisation();
//			AlertsnetApplication.medicalRecordsData = this.listMedicalRecords;
//			
//		}

		this.dataInitilisation();
		
		//System.out.println(listMedicalRecords.get(1).getMedications().get(1) + " ¨¨¨¨¨¨  " );
		System.out.println(listMedicalRecords.get(1).toString());
		return listMedicalRecords;
		
	}
	
	
	
	@Override
	public int insertMediacalRecord(MedicalRecord medicalRecord) throws IOException {
//		if (AlertsnetApplication.medicalRecordsData.isEmpty()) {
//			
//			this.dataInitilisation();
//			this.listMediacalRecords.add(person);
//			AlertsnetApplication.personsData = this.listDePersons;
//			
//			return 1;
//		} else {
//			
//			AlertsnetApplication.personsData.add(person);
//			
//			return 1;
//		}
		return 0;
	}

	

	@Override
	public int deleteMedicalRecord(MedicalRecord medicalRecord) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMedicalRecord(MedicalRecord medicalRecord, MedicalRecord newMedicalRecord) throws IOException {
		// TODO Auto-generated method stub
		return 0;
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
