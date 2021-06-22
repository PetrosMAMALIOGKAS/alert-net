package com.simplon.safety.alertsnet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id_address;
	
	public String rue_name_number;
	public String city;
	public String zip;
	
	@ManyToOne
	@JoinColumn( name =  "firestation_id")
	public Firestation firestation_responsable;

	
	public Address() {}
	
	public Address(AddressBuilder builder) {
		this.id_address = builder.id_address;
		this.rue_name_number = builder.rue_name_number;
		this.city = builder.city;
		this.zip = builder.zip;
		this.firestation_responsable = builder.firestation_responsable;
	}
	
	public Long getId_address() {
		return id_address;
	}

	public void setId_address(Long id_address) {
		this.id_address = id_address;
	}

	public String getRue_name_number() {
		return rue_name_number;
	}

	public void setRue_name_number(String rue_name_number) {
		this.rue_name_number = rue_name_number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public Firestation getFirestation_responsable() {
		return firestation_responsable;
	}

	public void setFirestation_responsable(Firestation firestation_responsable) {
		this.firestation_responsable = firestation_responsable;
	}
	

	@Override
	public String toString() {
		return "Address [id_address=" + id_address + ", rue_name_number=" + rue_name_number + ", city=" + city
				+ ", zip=" + zip + "]";
	}

	public static class AddressBuilder {
		private Long id_address;
		private String rue_name_number;
		private String city;
		private String zip;
		private Firestation firestation_responsable;

		public AddressBuilder id_address(Long id_address) {
			this.id_address = id_address;
			return this;
		}

		public AddressBuilder rue_name_number(String rue_name_number) {
			this.rue_name_number = rue_name_number;
			return this;
		}

		public AddressBuilder city(String city) {
			this.city = city;
			return this;
		}

		public AddressBuilder zip(String zip) {
			this.zip = zip;
			return this;
		}
		
		public AddressBuilder firestation_responsable(Firestation firestation_responsable) {
			this.firestation_responsable = firestation_responsable;
			return this;
		}

		public Address build() {
			return new Address(this);
		}
	}

}