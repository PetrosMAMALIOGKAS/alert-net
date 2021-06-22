package com.simplon.safety.alertsnet.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.simplon.safety.alertsnet.model.Firestation;

class FirestationAccessServiceTest {
	
	private FirestationAccessService firestationAccessService;
	
	@BeforeEach
	void setup() {
		this.firestationAccessService = new FirestationAccessService();
	}

	@Test
	void should_addAnFirestationObjectInstance_insideTheObjectsFieldVariable() {
		
		// Given
		Firestation firestation = new Firestation.FirestationBuilder()
							                .station_designation("3") 
							                .build();
		Firestation expected =  new Firestation.FirestationBuilder()
							                .station_designation("3") 
							                .build();
		// When
		firestationAccessService.firestations.add(firestation);
		
		// Then
		assertAll(
			() -> assertEquals(expected.getStation_designation(), firestationAccessService.firestations.get(0).getStation_designation()),
			() -> assertEquals(expected.getStation_designation(), firestationAccessService.firestations.get(0).getStation_designation())
		);
	}

	

}
