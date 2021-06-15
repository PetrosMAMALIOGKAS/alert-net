package com.simplon.safety.alertsnet.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	public String firstName;
	public String lastName;
	
	
	public String address;
	public String city;
	public String zip;

	public String phone;
	public String email;
	

	
	public Person() {}
	
	private Person(PersonBuilder builder) {
		
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address = builder.address;
		this.phone = builder.phone;
		this.email = builder.email;
		this.city = builder.city;
		this.zip = builder.zip;
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

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
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
	
	

	public String getCity()
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip) 
	{
		this.zip = zip;
	}

	@Override
	public String toString() 
	{
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address 
				+ ", phone=" + phone + ", email=" + email + "]";
	}

	public static class PersonBuilder 
	{
		private Long id;
		private String firstName;
		private String lastName;
		private String address;
		private String phone;
		private String email;
		private String city;
		private String zip;
		
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

		public PersonBuilder address(@JsonProperty("address") String address) 
		{
			this.address = address;
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
		
		public PersonBuilder city(@JsonProperty("city") String city) 
		{
			this.city = city;
			return this;
		}
		
		public PersonBuilder zip(@JsonProperty("zip") String zip) 
		{
			this.zip = zip;
			return this;
		}

		public Person build() 
		{
			return new Person(this);
		}
	}
}
