package com.simplon.safety.alertsnet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.simplon.safety.alertsnet.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
//	@Query("select a.city "
//			+ "from  address a "
//			+ "where  a.city = :city "
//			+ "and    a.rue_name_number = :rue_name_number "
//			+ "and    a.zip = :zip")
//	String getAddressId_IfExists(@Param("city") String city, @Param("rue_name_number") String rue_name_number, @Param("zip") String zip);
	
	@Query("select * from address ")
	List<Address> getAddresses();
	
}