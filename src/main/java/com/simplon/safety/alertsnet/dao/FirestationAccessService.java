package com.simplon.safety.alertsnet.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.simplon.safety.alertsnet.AlertsnetApplication;
import com.simplon.safety.alertsnet.model.Firestation;


@Repository("FirestationAccessDao")
public class FirestationAccessService implements FirestationDao {
	
	public List<Firestation> firestations = new ArrayList<Firestation>();
	
	
	public void dataInitilisation() throws IOException {
		
		String input = readJsonFile();
		Any obj = JsonIterator.deserialize(input);
		Any listFirestations = obj.get("firestations");

		for (Any ligne : listFirestations) {
			
			this.firestations.add(new Firestation.FirestationBuilder()
						                    .address(ligne.get("address").toString()) 
						                    .station(ligne.get("station").toString()) 
						                    .build()
						                    );
	
		}
	}
	
	@Override
	public List<Firestation> listAllfirestations() throws IOException {
		
		if (AlertsnetApplication.firestationsData.isEmpty()) {
			
			this.dataInitilisation();
			AlertsnetApplication.firestationsData = this.firestations;

		}
		
		return this.firestations;
	}
	

	@Override
	public int insertFirestation(Firestation firestation) throws IOException {

		if (AlertsnetApplication.firestationsData.isEmpty()) {
			
			this.dataInitilisation();
			this.firestations.add(firestation);
			AlertsnetApplication.firestationsData = this.firestations;
			
			return 1;
		} else {
			
			AlertsnetApplication.firestationsData.add(firestation);
			
			return 1;
		}
	}


	@Override
	public int deleteFirestation(Firestation firestation) throws IOException {
	
		if (AlertsnetApplication.firestationsData.isEmpty()) {
			
			this.dataInitilisation();
			AlertsnetApplication.firestationsData = this.firestations;
			
		}
		
		AlertsnetApplication.firestationsData.removeIf(firestationInList -> (   
				firestation.getAddress().equals(firestationInList.getAddress()) 
				&& firestation.getStation().equals(firestationInList.getStation())));
	
		return 1;
	}

	@Override
	public int updateFirestation(Firestation oldFirestation, Firestation newFirestation) throws IOException {

		if (AlertsnetApplication.firestationsData.isEmpty()) {
			
			this.dataInitilisation();
			AlertsnetApplication.firestationsData = this.firestations;
			
		}
		
		int indice = findIndiceDeFirestation(oldFirestation);
		
		Firestation newData = new Firestation.FirestationBuilder()
				                             .address(oldFirestation.getAddress())
				                             .station(newFirestation.getStation())
				                             .build();
		
		try {
			
			firestations.set(indice, newData);
			System.out.println("first value (adress) found and changed its station");
			return 1;
			
		} catch (IndexOutOfBoundsException e) {
			
			System.out.println("address : " + oldFirestation.getAddress() + " not found");
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
	
	
	public int findIndiceDeFirestation(Firestation firestationToChange) {
		int counter = 0;
		
		for (Firestation firestation : firestations ) {
			if (firestation.getAddress().equals(firestationToChange.getAddress()) ) {
				
				return counter;
			}

			counter++;			
		}
		
		return -1;
	
	}
	
}
