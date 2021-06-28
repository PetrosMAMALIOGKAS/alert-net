package com.simplon.safety.alertsnet.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class MedicalRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id_mediacalRecord;
	public String birthdate;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "s_associer", 
        joinColumns = { @JoinColumn(name = "id_mediacalRecord") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_medication") }
    )
    Set<Medication> medications = new HashSet<>();

	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "contenir", 
        joinColumns = { @JoinColumn(name = "id_mediacalRecord") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_allergie") }
    )
    Set<Allergie> allergies = new HashSet<>();


	public MedicalRecord() {}
	
	public MedicalRecord(MedicalRecordBuilder builder) {
		
		this.birthdate = builder.birthdate;
		this.medications = builder.medications;
		this.allergies = builder.allergies;
	}


	public String getBirthdate()
	{
		return birthdate;
	}

	public void setBirthdate(String birthdate)
	{
		this.birthdate = birthdate;
	}
	
	public Long getId_mediacalRecord() 
	{
		return id_mediacalRecord;
	}

	public void setId_mediacalRecord(Long id_mediacalRecord)
	{
		this.id_mediacalRecord = id_mediacalRecord;
	}

	public Set<Medication> getMedications()
	{
		return medications;
	}

	public void setMedications(Set<Medication> medications) 
	{
		this.medications = medications;
	}


	public Set<Allergie> getAllergies() {
		
		return allergies;
	}

	public void setAllergies(Set<Allergie> allergies) {
		
		this.allergies = allergies;
	}
	




	public static class MedicalRecordBuilder {
		
		private String birthdate;
		private Set<Medication> medications;
		private Set<Allergie> allergies;
		
		public MedicalRecordBuilder() {}


		public MedicalRecordBuilder birthdate(@JsonProperty("birthdate") String birthdate)
		{
			this.birthdate = birthdate;
			return this;
		}

		public MedicalRecordBuilder medications(Set<Medication> medications)
		{
			this.medications = medications;
			return this;
		}

		public MedicalRecordBuilder allergies(@JsonProperty("allergies") Set<Allergie> allergies) {
			
			this.allergies = allergies;
			return this;
		}

		public MedicalRecord build() {
			
			return new MedicalRecord(this);
		}
	}
}
