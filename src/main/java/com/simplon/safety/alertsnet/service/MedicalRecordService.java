package com.simplon.safety.alertsnet.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simplon.safety.alertsnet.dao.MedicalRecordDao;
import com.simplon.safety.alertsnet.model.MedicalRecord;


@Service
public class MedicalRecordService {
	
	private final MedicalRecordDao medicalRecordDao;
	
	@Autowired
	public MedicalRecordService(@Qualifier("MediacalRecordAccessDao") MedicalRecordDao medicalRecordDao) {
		this.medicalRecordDao = medicalRecordDao;
	}
	
	public List<MedicalRecord> listAllMedicalRecords() throws IOException {
		return medicalRecordDao.listAllMedicalRecords();
	}
	
	public int addMediacalRecord(MedicalRecord medicalRecord) throws IOException {
		return medicalRecordDao.insertMediacalRecord(medicalRecord);
	}
	
	public int deletePerson(MedicalRecord medicalRecord) throws IOException {
		return medicalRecordDao.deleteMedicalRecord(medicalRecord);
	}
	
	public int updatePerson(MedicalRecord oldMedicalRecord, MedicalRecord newMedicalRecord) throws IOException {
		return medicalRecordDao.updateMedicalRecord(oldMedicalRecord, newMedicalRecord);
	}

}
