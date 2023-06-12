package com.app.service;

import com.app.dto.LoginRequestDto;
import com.app.dto.UserDto;
import com.app.pojos.User;

public interface UserService {

	User add(UserDto transientUser);

	String delete(Long id);

	User fetchById(Long id);

	User update(User detachedUser);
	
	User authenticateEmp(LoginRequestDto dto);


}
