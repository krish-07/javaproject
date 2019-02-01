package com.pointel.poc.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointel.poc.db.entity.Login;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public Login validateUser(String identityName, String watchWord) {
		return loginRepository.validateUser(identityName, watchWord);
	}

}
