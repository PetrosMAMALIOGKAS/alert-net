package com.simplon.safety.alertsnet.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.simplon.safety.alertsnet.model.Address;
import com.simplon.safety.alertsnet.model.Firestation;


@Repository("FirestationAccessDao")
public class FirestationAccessService implements FirestationDao {
	
	public List<Firestation> firestations = new ArrayList<Firestation>();

	@Autowired                 
    private FirestationRepository firestationRepository;
	
	public void dataInitilisation() throws IOException {
		
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any listFirestations = obj.get("firestations");

		for (Any ligne : listFirestations) {

			this.firestations.add(new Firestation.FirestationBuilder()
							                    .station_designation(ligne.get("station").toString()) 
							                    .build()
							                    );
		}
	}
	

	@Override
	public long getIdFirestationResponsable_withAddress(String rueNameNumber) throws IOException {
		
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any listFirestations = obj.get("firestations");
		
		String station_designation = "";
		for (Any ligne : listFirestations) {
			if (rueNameNumber.equals(ligne.get("address").toString())) {
				station_designation = ligne.get("station").toString();
			}
		}
		
		long firestationid = firestationRepository.getFirestationId_IfExists(station_designation);
		
		return firestationid;
		

	}
	
	
	@Override
	public List<Firestation> listAllfirestations() throws IOException 
	{
		// TODO
//		if (AlertsnetApplication.firestationsData.isEmpty()) {
//			
//			this.dataInitilisation();
//			AlertsnetApplication.firestationsData = this.firestations;
//
//		}
		
		return this.firestations;
	}
	
	public Firestation insertFirestation(Address address) throws IOException 
	{
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any listFirestations = obj.get("firestations");
		
		String rueNameNumber = address.getRue_name_number();
		
		for (Any ligne : listFirestations) {
			if (rueNameNumber.equals(ligne.get("address").toString())) {
				Firestation queryResult = firestationRepository.getFirestation_IfExists(ligne.get("station").toString());
				
				if (queryResult == null) {
					queryResult = new Firestation.FirestationBuilder()
							                     .station_designation(ligne.get("station").toString())
							                     .build();
					queryResult = firestationRepository.save(queryResult);
				}
				return queryResult;
			}
		}
		return null;
	}
	

	@Override
	public long insertFirestation(Firestation firestation) throws IOException {

		//long idFirestation;
		firestationRepository.save(firestation);
		
		return 1;
		
//		   try {
//				Address dbResult = addressRepository.getAddressId_IfExists(address.getCity(), address.getRue_name_number(), address.getZip());
//				idAddress = dbResult.getId_address();
//				return idAddress;
//			} catch (Exception e) {
//		
//				
//				Address dbResult = addressRepository.getAddressId_IfExists(address.getCity(), address.getRue_name_number(), address.getZip());
//				idAddress = dbResult.getId_address();
//				return idAddress;
//			}   

	}

	@Override
	public int deleteFirestation(Firestation firestation) throws IOException
	{
		// TODO
//		if (AlertsnetApplication.firestationsData.isEmpty()) {
//			
//			this.dataInitilisation();
//			AlertsnetApplication.firestationsData = this.firestations;
//			
//		}
//		
//		AlertsnetApplication.firestationsData.removeIf(firestationInList -> (   
//				firestation.getAddress().equals(firestationInList.getAddress()) 
//				&& firestation.getStation().equals(firestationInList.getStation())));
	
		return 1;
	}

	@Override
	public int updateFirestation(Firestation oldFirestation, Firestation newFirestation) throws IOException 
	{
		// TODO
//		if (AlertsnetApplication.firestationsData.isEmpty()) {
//			
//			this.dataInitilisation();
//			AlertsnetApplication.firestationsData = this.firestations;
//			
//		}
		
	
		
//		Firestation newData = new Firestation.FirestationBuilder()
//				                             .address(oldFirestation.getAddress())
//				                             .station(newFirestation.getStation())
//				                             .build();
//		
		try {
			
		//	firestations.set(indice, newData);
			System.out.println("first value (adress) found and changed its station");
			return 1;
			
		} catch (IndexOutOfBoundsException e) {
			
		//	System.out.println("address : " + oldFirestation.getAddress() + " not found");
			return -1;
		}
	}
	
	public String readJsonFile() throws IOException { 
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
