package com.simplon.safety.alertsnet.dao;

import java.io.IOException;
import java.util.List;

import com.simplon.safety.alertsnet.model.Firestation;


public interface FirestationDao {
	
		
    int insertFirestation(Firestation firestation) throws IOException;
	
	List<Firestation> listAllfirestations() throws IOException;
	
	int deleteFirestation(Firestation firestation) throws IOException;
	
	int updateFirestation(Firestation oldFirestation, Firestation newFirestation) throws IOException;
	
}
