package com.simplon.safety.alertsnet.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.safety.alertsnet.model.MedicalRecord;
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
		//System.out.println(medicalRecordService.listAllMedicalRecords().get(1).medications.get(1).toString());
		return medicalRecordService.listAllMedicalRecords();
	}
	
	

}
