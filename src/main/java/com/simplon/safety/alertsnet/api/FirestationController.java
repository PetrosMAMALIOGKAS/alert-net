package com.simplon.safety.alertsnet.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.safety.alertsnet.model.Firestation;
import com.simplon.safety.alertsnet.service.FirestationService;


@RequestMapping("firestation")
@RestController
public class FirestationController {
	
	private final FirestationService firestationService;
	
	@Autowired
	public FirestationController(FirestationService firestationService) {
		
		this.firestationService = firestationService;
	}
	
	@GetMapping
	public List<Firestation> listAllfirestations() throws IOException {

		return firestationService.listAllfirestations();
	}
	
	@PostMapping
	public void addFirestation(@RequestBody Firestation firestation) throws IOException {

		firestationService.insertFirestation(firestation);
	}
	
	@DeleteMapping
	public void deleteFirestation(@RequestBody Firestation firestation) throws IOException {

		firestationService.deleteFirestation(firestation);
	}
	
	@PutMapping
	public void updatePerson(@RequestParam("address") String address,
			                 @RequestBody Firestation newFirestationCaserne   )  throws IOException {
		
		Firestation firestationToChange = new Firestation.FirestationBuilder()
				                     .address(address)
				                     .build();
		
		firestationService.updateFirestation(firestationToChange, newFirestationCaserne);
	}
	

}
