package com.simplon.safety.alertsnet.dao;

import java.io.IOException;
import java.util.List;

import com.simplon.safety.alertsnet.model.MedicalRecord;


public interface MedicalRecordDao {
	
		
	    int insertMediacalRecord(MedicalRecord medicalRecord) throws IOException;
		
		List<MedicalRecord> listAllMedicalRecords() throws IOException;
		
		int deleteMedicalRecord(MedicalRecord medicalRecord) throws IOException;
		
		int updateMedicalRecord(MedicalRecord medicalRecord, MedicalRecord newMedicalRecord) throws IOException;

		long initMediacalRecordTable() throws IOException;

		long getMedicalRecordId_ByFirstLastName(String personFirstName, String personLastName) throws IOException;
		
}
