package com.simplon.safety.alertsnet.model;

import java.util.List;
import java.util.Map;

public class MedicalRecord {
	public String firstName;
	public String lastName;
	public String birthdate;
	public Map<String, String> medications;
	public List<String> allergies;

	public MedicalRecord() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Map<String, String> getMedications() {
		return medications;
	}

	public void setMedications(Map<String, String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	public static class MedicalRecordBuilder {
		private String firstName;
		private String lastName;
		private String birthdate;
		private Map<String, String> medications;
		private List<String> allergies;

		public MedicalRecordBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public MedicalRecordBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public MedicalRecordBuilder birthdate(String birthdate) {
			this.birthdate = birthdate;
			return this;
		}

		public MedicalRecordBuilder medications(Map<String, String> medications) {
			this.medications = medications;
			return this;
		}

		public MedicalRecordBuilder allergies(List<String> allergies) {
			this.allergies = allergies;
			return this;
		}

		public MedicalRecord build() {
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord.firstName = firstName;
			medicalRecord.lastName = lastName;
			medicalRecord.birthdate = birthdate;
			medicalRecord.medications = medications;
			medicalRecord.allergies = allergies;
			return medicalRecord;
		}
	}
}
