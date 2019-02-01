package com.pointel.poc.user.control;

import com.pointel.poc.db.entity.Login;

public interface UserManagementService {

	boolean addUser(Login login);

	String getAllUser();

	boolean editUser(Login login);

	boolean removeUser(long userId);


}
