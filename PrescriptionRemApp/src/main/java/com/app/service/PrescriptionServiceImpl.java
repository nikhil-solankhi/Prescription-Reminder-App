package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.PrescriptionDto;
import com.app.pojos.Prescription;
import com.app.pojos.User;
import com.app.repository.PrescriptionRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {
	
	@Autowired
	private PrescriptionRepository PrescriptionRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<Prescription> getAllPrescriptions() {
		// TODO Auto-generated method stub
		return PrescriptionRepo.findAll();
	}

	@Override
	public User add(PrescriptionDto transientPrescription) {
		System.out.println(transientPrescription);
		Prescription prescription = mapper.map(transientPrescription, Prescription.class);
		User user = userRepo.findById(transientPrescription.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Prescription ID !!!!!"));
		prescription.setUser(user);
		user.getPrescription().add(prescription);
		return userRepo.save(user);
	}

	@Override
	public String delete(Long id) {
		// TODO Auto-generated method stub
		if (PrescriptionRepo.existsById(id)) {
			PrescriptionRepo.deleteById(id);
			return "Prescription details deleted ....";
		}
		return "Deletion Failed : Invalid Id !!!!!!!!!!!";
	}

	@Override
	public Prescription fetchById(Long empId) {
		// TODO Auto-generated method stub
		return PrescriptionRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Invalid ID !!!!!"));
	}

	@Override
	public Prescription update(Prescription detachedPrescription) {
		// TODO Auto-generated method stub
		if (PrescriptionRepo.existsById(detachedPrescription.getId())) {
			return PrescriptionRepo.save(detachedPrescription);
		}
		throw new ResourceNotFoundException("Invalid Id : Updation Failed!!!!!!!!");	}
	
	@Override
	public List<Prescription> getPresDetailsbyUserId(Long UserId) {
		User user = userRepo.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Prescription ID !!!!!"));
		return PrescriptionRepo.findPrescriptionsByUser(user);
	}
	
}
