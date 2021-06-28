package com.simplon.safety.alertsnet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.simplon.safety.alertsnet.model.Firestation;

@Repository
@Transactional
public interface FirestationRepository extends JpaRepository<Firestation, Long> {
	
	@Query(value="select *"
			+ "from  firestation f "
			+ "where  f.station_designation = :station_designation "
			, nativeQuery = true)
	Firestation getFirestation_IfExists(@Param("station_designation") String station_designation);
	
	@Query(value="select f.id_firestation"
			+ "from  firestation f "
			+ "where  f.station_designation = :station_designation "
			, nativeQuery = true)
	long getFirestationId_IfExists(@Param("station_designation") String station_designation);
	
}
