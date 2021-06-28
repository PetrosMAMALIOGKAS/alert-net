package com.simplon.safety.alertsnet.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Allergie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id_allergie;
	
	@ManyToMany(mappedBy = "allergies")
	public Set<MedicalRecord> medicalRecords = new HashSet<>();

	public String designation;
	
	
	public Allergie(AllergieBuilder builder) {
		this.id_allergie = builder.id_allergie;
		this.medicalRecords = builder.medicalRecords;
		this.designation = builder.designation;
	}

	public Long getId_allergie() {
		return id_allergie;
	}

	public void setId_allergie(Long id_allergie) {
		this.id_allergie = id_allergie;
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
		return "Allergie [id_allergie=" + id_allergie + ", medicalRecords=" + medicalRecords + ", designation="
				+ designation + "]";
	}

	public static class AllergieBuilder {
		private Long id_allergie;
		private Set<MedicalRecord> medicalRecords;
		private String designation;

		public AllergieBuilder id_allergie(Long id_allergie) {
			this.id_allergie = id_allergie;
			return this;
		}

		public AllergieBuilder medicalRecords(Set<MedicalRecord> medicalRecords) {
			this.medicalRecords = medicalRecords;
			return this;
		}

		public AllergieBuilder designation(String designation) {
			this.designation = designation;
			return this;
		}

		public Allergie build() {
			return new Allergie(this);
		}
	}

	
}
