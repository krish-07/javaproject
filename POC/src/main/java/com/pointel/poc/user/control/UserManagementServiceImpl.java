package com.pointel.poc.user.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointel.poc.db.entity.Login;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserManagementRepository userManagementRepository;

	@Override
	public boolean addUser(Login login) {
		return userManagementRepository.addNewUser(login);
	}

	@Override
	public String getAllUser() {
		 return userManagementRepository.getAllUser();
	}

	@Override
	public boolean editUser(Login login) {
		return userManagementRepository.editUser(login);
	}

	@Override
	public boolean removeUser(long userId) {
		return userManagementRepository.deleteUser(userId);
	}
	

}
