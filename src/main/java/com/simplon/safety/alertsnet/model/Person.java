package com.simplon.safety.alertsnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
	
	/*   for the post  cause not working with builder pattern  */
	public Person(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
			@JsonProperty("address") String address, @JsonProperty("city") String city, @JsonProperty("zip") String zip,
			@JsonProperty("phone") String phone, @JsonProperty("email") String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	/*
	
		public Person(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = null;
			this.city = null;
			this.zip = null;
			this.phone = null;
			this.email = null;
		}
	*/

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

	

	public static class Builder {
		private String firstName;
		private String lastName;
		private String address;
		private String city;
		private String zip;
		private String phone;
		private String email;
		

		public Builder firstName(@JsonProperty("firstName") String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(@JsonProperty("lastName") String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder address(@JsonProperty("address") String address) {
			this.address = address;
			return this;
		}

		public Builder city(@JsonProperty("city") String city) {
			this.city = city;
			return this;
		}

		public Builder zip(@JsonProperty("zip") String zip) {
			this.zip = zip;
			return this;
		}

		public Builder phone(@JsonProperty("phone") String phone) {
			this.phone = phone;
			return this;
		}

		public Builder email(@JsonProperty("email") String email) {
			this.email = email;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
		/*
		private Person(firstName, lastName, address, city, zip, phone, email) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.city = city;
			this.zip = zip;
			this.phone = phone;
			this.email = email;
		}
		*/
	}

	private Person(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address = builder.address;
		this.city = builder.city;
		this.zip = builder.zip;
		this.phone = builder.phone;
		this.email = builder.email;
	}
	

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", zip=" + zip + ", phone=" + phone + ", email=" + email + "]";
	}
	
	
}
