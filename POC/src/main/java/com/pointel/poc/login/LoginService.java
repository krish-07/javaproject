package com.pointel.poc.login;

import com.pointel.poc.db.entity.Login;

public interface LoginService {

	Login validateUser(String identityName, String watchWord);

}
