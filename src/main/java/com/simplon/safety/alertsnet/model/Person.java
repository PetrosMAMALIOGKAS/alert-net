package com.simplon.safety.alertsnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String zip;
	public String phone;
	public String email;
	
	public Person() {}
	
	private Person(PersonBuilder builder) {
		
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address = builder.address;
		this.city = builder.city;
		this.zip = builder.zip;
		this.phone = builder.phone;
		this.email = builder.email;
	}

	public String getFirstName() {
		
		return firstName;
	}

	public String getLastName() {
		
		return lastName;
	}

	public String getAddress() {
		
		return address;
	}

	public String getCity() {
		
		return city;
	}

	public String getZip() {
		
		return zip;
	}

	public String getPhone() {
		
		return phone;
	}

	public String getEmail() {
		
		return email;
	}
	
	@Override
	public String toString() {
		
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", zip=" + zip + ", phone=" + phone + ", email=" + email + "]";
	}

	public static class PersonBuilder {
		
		private String firstName;
		private String lastName;
		private String address;
		private String city;
		private String zip;
		private String phone;
		private String email;
		
		public PersonBuilder() {}
		
		public PersonBuilder firstName(@JsonProperty("firstName") String firstName) {
			
			this.firstName = firstName;
			return this;
		}

		public PersonBuilder lastName(@JsonProperty("lastName") String lastName) {
			
			this.lastName = lastName;
			return this;
		}

		public PersonBuilder address(@JsonProperty("address") String address) {
			
			this.address = address;
			return this;
		}

		public PersonBuilder city(@JsonProperty("city") String city) {
			
			this.city = city;
			return this;
		}

		public PersonBuilder zip(@JsonProperty("zip") String zip) {
			
			this.zip = zip;
			return this;
		}

		public PersonBuilder phone(@JsonProperty("phone") String phone) {
			
			this.phone = phone;
			return this;
		}

		public PersonBuilder email(@JsonProperty("email") String email) {
			
			this.email = email;
			return this;
		}

		public Person build() {
			
			return new Person(this);
		}
	}
}
