package com.simplon.safety.alertsnet.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.safety.alertsnet.model.MedicalRecord;
import com.simplon.safety.alertsnet.model.Person;
import com.simplon.safety.alertsnet.service.MedicalRecordService;


@RequestMapping("medicalRecord")
@RestController
public class MedicalRecordController {
	
	private final MedicalRecordService medicalRecordService;

	@Autowired
	public MedicalRecordController(MedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}
	
	@GetMapping
	public List<MedicalRecord> listAllMedicalRecords() throws IOException {

		return medicalRecordService.listAllMedicalRecords();
	}
	
	@PostMapping
	public void addMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
		
		medicalRecordService.addMediacalRecord(medicalRecord);
	}
	
	
	@PutMapping
	public void updateMedicalRecord(@RequestParam("lastName") String lastName,
					                 @RequestParam("firstName") String firstName,
					                 @RequestBody MedicalRecord newMedicalRecord )  throws IOException {
				
		MedicalRecord oldMedicalRecord = new MedicalRecord.MedicalRecordBuilder()
										                .firstName(firstName)
										                .lastName(lastName)
										                .build();
		
		medicalRecordService.updatePerson(oldMedicalRecord, newMedicalRecord);
	}
	
	@DeleteMapping
	public void deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
		
		medicalRecordService.deletePerson(medicalRecord);
	}
	
}
