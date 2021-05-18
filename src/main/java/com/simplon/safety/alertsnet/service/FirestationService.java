package com.simplon.safety.alertsnet.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simplon.safety.alertsnet.dao.FirestationDao;
import com.simplon.safety.alertsnet.model.Firestation;


@Service
public class FirestationService {
	
	private final FirestationDao firestationDao;
	
	@Autowired
	public FirestationService(@Qualifier("FirestationAccessDao") FirestationDao firestationDao) {
		this.firestationDao = firestationDao;
	}
	
	public int insertFirestation(Firestation firestation) throws IOException {
		return firestationDao.insertFirestation(firestation);
	}

	public List<Firestation> listAllfirestations() throws IOException {
		return firestationDao.listAllfirestations();
	}

	public int deleteFirestation(Firestation firestation) throws IOException {
		return firestationDao.deleteFirestation(firestation);
	}

	public int updateFirestation(Firestation firestationToChange, Firestation newFirestationCaserne) throws IOException {
		return firestationDao.updateFirestation(firestationToChange, newFirestationCaserne);
	}
}
