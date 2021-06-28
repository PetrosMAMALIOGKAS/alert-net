package com.simplon.safety.alertsnet.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.simplon.safety.alertsnet.model.Address;

public interface AddressDao {
	
	Address insertAddress(Address address) throws IOException;
	
	List<Address> listAllAddress() throws IOException;
	
	Optional<Address> getAddressById(Long id);
	
	int deleteAddress(Address address) throws IOException;
	
	int deleteAddressById(Long id);
	
	int updateAddress(Address address, Address newAddress) throws IOException;
	
	int updateAddressById(Long id, Address newAddress);
}