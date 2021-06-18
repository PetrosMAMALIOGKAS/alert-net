package com.simplon.safety.alertsnet.model;



import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id_address;
	
	@OneToMany(
			mappedBy = "person_address",
			cascade = CascadeType.ALL,
			orphanRemoval = true )
	private Collection<Person> personsOnThisAddress = new ArrayList<>();
	
	public String rue_name_number;
	public String city;
	public String zip;
	
	public Address() {}
	
	public Address(AddressBuilder builder) {
		this.id_address = builder.id_address;
		this.rue_name_number = builder.rue_name_number;
		this.city = builder.city;
		this.zip = builder.zip;
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
	


	public Collection<Person> getPersonsOnThisAddress() {
		return personsOnThisAddress;
	}

	public void setPersonsOnThisAddress(Collection<Person> personsOnThisAddress) {
		this.personsOnThisAddress = personsOnThisAddress;
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

		public Address build() {
			return new Address(this);
		}
	}


}