package com.app.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.app.dto.PrescriptionDto;
import com.app.pojos.Prescription;
import com.app.pojos.User;


public interface PrescriptionService {
	List<Prescription> getAllPrescriptions();

	User add(PrescriptionDto transientPrescription);

	String delete(Long id);

	Prescription fetchById(Long id);

	Prescription update(Prescription detachedPrescription);
	
	public List<Prescription> getPresDetailsbyUserId(Long UserId);
    

}
