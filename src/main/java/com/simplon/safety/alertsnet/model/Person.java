package com.simplon.safety.alertsnet.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	public String firstName;
	public String lastName;
	
	@ManyToOne
	@JoinColumn( name =  "address_id")
	public Address person_address;

	public String phone;
	public String email;
	
	@OneToOne
	@JoinColumn( name =  "id_mediacalRecord")
	public MedicalRecord medicalRecord;
	

	
	public Person() {}
	
	private Person(PersonBuilder builder) {
		
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.person_address = builder.person_address;
		this.phone = builder.phone;
		this.email = builder.email;
		this.medicalRecord = builder.medicalRecord;
	}

	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getPhone() 
	{
		return phone;
	}

	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	
	public Address getPerson_address() {
		return person_address;
	}

	public void setPerson_address(Address person_address) {
		this.person_address = person_address;
	}

	public MedicalRecord getMedicalRecord()
	{
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord)
	{
		this.medicalRecord = medicalRecord;
	}

//	@Override
//	public String toString() 
//	{
//		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", address=" 
//				+ ", phone=" + phone + ", email=" + email + "]";
//	}
	
	@Override
	public String toString() {
		return "PersonBuilder [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone="
				+ phone + ", email=" + email + ", person_address=" + person_address + ", medicalRecord="
				+ medicalRecord + "]";
	}

	

	public static class PersonBuilder {

		private Long id;
		private String firstName;
		private String lastName;
		private String phone;
		private String email;
		private Address person_address;
		private MedicalRecord medicalRecord;

		public PersonBuilder() {}
		
		public PersonBuilder id(@JsonProperty("id") Long id) 
		{
			this.id = id;
			return this;
		}
		
		public PersonBuilder firstName(@JsonProperty("firstName") String firstName) 
		{
			this.firstName = firstName;
			return this;
		}

		public PersonBuilder lastName(@JsonProperty("lastName") String lastName) 
		{
			this.lastName = lastName;
			return this;
		}

		public PersonBuilder phone(@JsonProperty("phone") String phone) 
		{
			this.phone = phone;
			return this;
		}

		public PersonBuilder email(@JsonProperty("email") String email) 
		{
			this.email = email;
			return this;
		}
		
		
		public PersonBuilder person_address( Address person_address) 
		{
			this.person_address = person_address;
			return this;
		}
		
		public PersonBuilder medicalRecord( MedicalRecord medicalRecord) 
		{
			this.medicalRecord = medicalRecord;
			return this;
		}


		public Person build() 
		{
			return new Person(this);
		}
	}
}
