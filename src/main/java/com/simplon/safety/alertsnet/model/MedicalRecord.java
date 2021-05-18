package com.simplon.safety.alertsnet.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicalRecord {
	public String firstName;
	public String lastName;
	public String birthdate;
	public List<String> medications;
	public List<String> allergies;

	public MedicalRecord() {
	}
	
	public MedicalRecord(MedicalRecordBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.birthdate = builder.birthdate;
		this.medications = builder.medications;
		this.allergies = builder.allergies;
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

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
	

	@Override
	public String toString() {
		return "MedicalRecord [firstName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthdate
				+ ", medications=" + medications + ", allergies=" + allergies + "]";
	}



	public static class MedicalRecordBuilder {
		private String firstName;
		private String lastName;
		private String birthdate;
		private List<String> medications;
		private List<String> allergies;
		
		public MedicalRecordBuilder() {
		}

		public MedicalRecordBuilder firstName(@JsonProperty("firstName") String firstName) {
			this.firstName = firstName;
			return this;
		}

		public MedicalRecordBuilder lastName(@JsonProperty("lastName") String lastName) {
			this.lastName = lastName;
			return this;
		}

		public MedicalRecordBuilder birthdate(@JsonProperty("birthdate") String birthdate) {
			this.birthdate = birthdate;
			return this;
		}

		public MedicalRecordBuilder medications(@JsonProperty("medications") List<String> medications) {
			this.medications = medications;
			return this;
		}

		public MedicalRecordBuilder allergies(@JsonProperty("allergies") List<String> allergies) {
			this.allergies = allergies;
			return this;
		}

		public MedicalRecord build() {
			return new MedicalRecord(this);
		}
		
	}
}
