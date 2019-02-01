package com.pointel.poc.login;

import com.pointel.poc.db.entity.Login;

public interface LoginRepository {
	Login validateUser(String identityName, String watchWord);
}
