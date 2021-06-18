package com.simplon.safety.alertsnet.model;

public class PersonInsertion {

	public Person person;
	public Address address;
	
	public PersonInsertion(PersonInsertionBuilder personInsertionBuilder) 
	{
		this.person = personInsertionBuilder.person;
		this.address = personInsertionBuilder.address;
	}
	
	public PersonInsertion() {}

	
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PersonInsertion [person=" + person + ", address=" + address + "]";
	}

	public static class PersonInsertionBuilder {
		private Person person;
		private Address address;

		public PersonInsertionBuilder person(Person person) {
			this.person = person;
			return this;
		}

		public PersonInsertionBuilder address(Address address) {
			this.address = address;
			return this;
		}

		public PersonInsertion build() {
			return new PersonInsertion(this);
		}
	}

	
}
