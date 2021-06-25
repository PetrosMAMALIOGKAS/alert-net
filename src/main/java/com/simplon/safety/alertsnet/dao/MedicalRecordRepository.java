package com.simplon.safety.alertsnet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.simplon.safety.alertsnet.model.MedicalRecord;


@Repository
@Transactional
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
	
	@Query(value="select id_mediacal_record "
			+ "from  medical_record m "
			+ "where  m.birthdate = :birthdate " , nativeQuery = true)
	long getId_byDate(@Param("birthdate") String birthdate);

}



