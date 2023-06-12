package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.LoginRequestDto;
import com.app.dto.UserDto;
import com.app.pojos.Prescription;
import com.app.pojos.User;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PrescriptionService prescriptionService;

	@Override
	public User add(UserDto transientUser) {
		User User = mapper.map(transientUser, User.class);
		User.setEnabled(false);
//		User.setPassword(enc.encode(User.getPassword()));
		return userRepo.save(User);
	}

	@Override
	public String delete(Long id) {
		// TODO Auto-generated method stub
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return "User details deleted ....";
		}
		return "Deletion Failed : Invalid Id !!!!!!!!!!!";
	}

	@Override
	public User fetchById(Long empId) {
		// TODO Auto-generated method stub
		return userRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Invalid ID !!!!!"));
	}

	@Override
	public User update(User detachedUser) {
		// TODO Auto-generated method stub
		if (userRepo.existsById(detachedUser.getId())) {
			return userRepo.save(detachedUser);
		}
		throw new ResourceNotFoundException("Invalid Id : Updation Failed!!!!!!!!");
	}

	@Override
	public User authenticateEmp(LoginRequestDto dto) {
		// TODO Auto-generated method stub
		
		return userRepo.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!"));
	}

}
