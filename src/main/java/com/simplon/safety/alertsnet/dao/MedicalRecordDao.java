package com.simplon.safety.alertsnet.dao;

import java.io.IOException;
import java.util.List;

import com.simplon.safety.alertsnet.model.MedicalRecord;
import com.simplon.safety.alertsnet.model.Person;


public interface MedicalRecordDao {
	
		
	    int insertMedicalRecord(MedicalRecord medicalRecord) throws IOException;
	    
	    MedicalRecord insertMedicalRecord(Person person) throws IOException;
		
		List<MedicalRecord> listAllMedicalRecords() throws IOException;
		
		int deleteMedicalRecord(MedicalRecord medicalRecord) throws IOException;
		
		int updateMedicalRecord(MedicalRecord medicalRecord, MedicalRecord newMedicalRecord) throws IOException;

		long getMedicalRecordId_ByFirstLastName(String personFirstName, String personLastName) throws IOException;
}
