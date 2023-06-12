package com.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Prescription;
import com.app.pojos.User;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	@Query("SELECT p FROM Prescription p WHERE p.startDate <= :currentDate AND p.endDate >= :currentDate AND p.isActive = true")
    List<Prescription> findActivePrescriptionsBetweenDates(LocalDate currentDate);
	
	List<Prescription> findPrescriptionsByUser	(User user);
}