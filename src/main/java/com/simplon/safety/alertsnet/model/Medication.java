package com.simplon.safety.alertsnet.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id_mediacation;

	@ManyToMany(mappedBy = "medications")
	public Set<MedicalRecord> medicalRecords = new HashSet<>();

	public String designation;
	
	public Medication(MedicationBuilder builder) {
		this.id_mediacation = builder.id_mediacation;
		this.medicalRecords = builder.medicalRecords;
		this.designation = builder.designation;
	}

	public Long getId_mediacation() {
		return id_mediacation;
	}

	public void setId_mediacation(Long id_mediacation) {
		this.id_mediacation = id_mediacation;
	}

	public Set<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(Set<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Medication [id_mediacation=" + id_mediacation + ", medicalRecords=" + medicalRecords + ", designation="
				+ designation + "]";
	}

	public static class MedicationBuilder {
		
		private Long id_mediacation;
		private Set<MedicalRecord> medicalRecords;
		private String designation;

		public MedicationBuilder id_mediacation(Long id_mediacation) 
		{
			this.id_mediacation = id_mediacation;
			return this;
		}

		public MedicationBuilder medicalRecords(Set<MedicalRecord> medicalRecords)
		{
			this.medicalRecords = medicalRecords;
			return this;
		}

		public MedicationBuilder designation(String designation) 
		{
			this.designation = designation;
			return this;
		}

		public Medication build() 
		{
			return new Medication(this);
		}
	}

	
}
