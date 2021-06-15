package com.simplon.safety.alertsnet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.simplon.safety.alertsnet.model.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {
	
  @Transactional
  @Modifying
  @Query("update Person p set p.address = :address where p.id = :id")
  void updatePersonAddress(@Param("address") String address, @Param("id") Long id);
  
  @Transactional
  @Modifying
  @Query("update Person p set p.zip = :zip where p.id = :id")
  void updatePersonZip(@Param("zip") String zip, @Param("id") Long id);
  
  @Transactional
  @Modifying
  @Query("update Person p set p.city = :city where p.id = :id")
  void updatePersonCity(@Param("city") String city, @Param("id") Long id);
  
  @Transactional
  @Modifying
  @Query("update Person p set p.phone = :phone where p.id = :id")
  void updatePersonPhone(@Param("phone") String phone, @Param("id") Long id);
  
  @Transactional
  @Modifying
  @Query("update Person p set p.email = :email where p.id = :id")
  void updatePersonEmail(@Param("email") String email, @Param("id") Long id);
}
