package com.simplon.safety.alertsnet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Firestation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id_firestation;
	
	public String station_designation;

	public Firestation() {}
	
	public Firestation(FirestationBuilder builder) {
		
		this.id_firestation = builder.id_firestation;
		this.station_designation = builder.station_designation;
	}
	

	public String getStation() {
		
		return station_designation;
	}

	public void setStation(String station_designation) {
		
		this.station_designation = station_designation;
	}

	
	public Long getId_firestation()
	{
		return id_firestation;
	}

	public void setId_firestation(Long id_firestation)
	{
		this.id_firestation = id_firestation;
	}

	public String getStation_designation() 
	{
		return station_designation;
	}

	public void setStation_designation(String station_designation)
	{
		this.station_designation = station_designation;
	}

	
	public static class FirestationBuilder {
		
		private String station_designation;
		private long id_firestation;
		
		public FirestationBuilder() {}

		public FirestationBuilder id_firestation(@JsonProperty("id_firestation") long id_firestation) {
			
			this.id_firestation = id_firestation;
			return this;
		}
		
		
		public FirestationBuilder station_designation(@JsonProperty("station_designation") String station_designation) {
			
			this.station_designation = station_designation;
			return this;
		}

		public Firestation build() {
			
			return new Firestation(this);
		}
	}

}
