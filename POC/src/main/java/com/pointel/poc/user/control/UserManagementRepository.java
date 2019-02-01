package com.pointel.poc.user.control;

import com.pointel.poc.db.entity.Login;

public interface UserManagementRepository {

	boolean addNewUser(Login login);

	boolean editUser(Login login);

	String getAllUser();

	boolean deleteUser(long userId);

}