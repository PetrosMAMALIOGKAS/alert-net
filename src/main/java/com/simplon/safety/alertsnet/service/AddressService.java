package com.simplon.safety.alertsnet.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simplon.safety.alertsnet.dao.AddressDao;


@Service
public class AddressService {
	
@SuppressWarnings("unused")
private final AddressDao addressDao;
	
	@Autowired
	public AddressService(@Qualifier("AddressAccessDao") AddressDao addressDao)
	{
		this.addressDao = addressDao;
	}
	

}
