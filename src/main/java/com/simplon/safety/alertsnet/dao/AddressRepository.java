package com.simplon.safety.alertsnet.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.simplon.safety.alertsnet.model.Address;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	@Query(value="select * "
			+ "from  address a "
			+ "where  a.city = :city "
			+ "and    a.rue_name_number = :rue_name_number "
			+ "and    a.zip = :zip", nativeQuery = true)
	Address getAddressId_IfExists(@Param("city") String city, @Param("rue_name_number") String rue_name_number, @Param("zip") String zip);
	
}