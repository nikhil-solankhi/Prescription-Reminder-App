package com.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.PrescriptionDto;
import com.app.pojos.Prescription;
import com.app.pojos.User;
import com.app.repository.PrescriptionRepository;
import com.app.service.PrescriptionService;


@RestController
@RequestMapping("/prescriptions")
@CrossOrigin
public class PrescriptionController {
	@Autowired
	private PrescriptionService prescriptionService;
	
	@Autowired
	private PrescriptionRepository prescriptionRepo;

	@GetMapping
	public List<Prescription> getAllPress() {
		LocalDate currentDate = LocalDate.now();
		return prescriptionRepo.findActivePrescriptionsBetweenDates(currentDate);
	}

	@PostMapping
	public User savePresDetails(@RequestBody PrescriptionDto transientPrescription) {
		transientPrescription.setActive(true);
		return prescriptionService.add(transientPrescription);

	}

	@DeleteMapping("/{id}")
	public ApiResponse deletePresDetails(@PathVariable Long id) {
	
		return new ApiResponse(prescriptionService.delete(id));
	}

	@GetMapping("/{id}")
	public Prescription getPresDetails(@PathVariable Long id) {
		return prescriptionService.fetchById(id);
	}

	@PutMapping
	public Prescription updatePresDetails(@RequestBody Prescription detachedPrescription) {
		return prescriptionService.update(detachedPrescription);
	}
	
	@GetMapping("/byuserid/{id}")
	public List<Prescription> loadPresDetailsbyUserId(@PathVariable Long id) {
		return prescriptionService.getPresDetailsbyUserId(id);
	}

}
