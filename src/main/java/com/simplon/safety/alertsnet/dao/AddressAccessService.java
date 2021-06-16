package com.simplon.safety.alertsnet.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.simplon.safety.alertsnet.model.Address;

@Repository("AddressAccessDao")
public class AddressAccessService implements AddressDao{
	
	List<Address> listDesAddress = new ArrayList<Address>();

	@Autowired                 
    private AddressRepository addressRepository;
	
	@Override
	public int initAddressTable() throws IOException 
	{
		this.dataInitilisation();
		

//		for (Address address : this.listDesAddress) {
//			long idAddress = addressRepository.getAddressId_IfExists(address.getCity(), address.getRue_name_number(), address.getZip());
//			System.out.println("idAddress = " + idAddress + " counter= " + counter);
//		}
//		
		
		addressRepository.saveAll(this.listDesAddress);
		
		return 1;
	}

	@Override
	public int insertAddress(Address address) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Address> listAllAddress() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Address> getAddressById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAddress(Address address) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAddressById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAddress(Address address, Address newAddress) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAddressById(Long id, Address newAddress) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void dataInitilisation() throws IOException
	{
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any listPerson = obj.get("persons");

		for (Any ligne : listPerson) {
			
			this.listDesAddress.add(new Address.AddressBuilder()
	                    		            .rue_name_number(ligne.get("address").toString()) 
						                    .city(ligne.get("city").toString()) 
						                    .zip(ligne.get("zip").toString()) 
						                    .build()
						                    );					
		}
	}
	
	public String readJsonFile() throws IOException
	{ 
		String persons = "";
		try {
		    persons = new String(
		      Files.readAllBytes(new File("src/main/resources/data.json").toPath()));
		}
		catch (Exception e) {
			System.out.println("errrrrrrrrrrrror :" + e);
		}
	   
	    return persons;
	}
}