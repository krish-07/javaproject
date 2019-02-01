package com.pointel.poc.user.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointel.poc.db.entity.Login;

@Transactional
@Repository
public class UserManagementRepositoryImpl implements UserManagementRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public boolean addNewUser(Login login) {
		boolean isAdded = false;
		try {

			if (checkUserName(login)) {
				entityManager.persist(login);
				isAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAdded;
	}

	private boolean checkUserName(Login login) {
		List<Login> loginList = getAllUserList();
		for (Login existingLogin : loginList) {
			if (existingLogin.getUserName().equalsIgnoreCase(login.getUserName()) && login.getUserId() == 0) {
				return false;
			}

		}
		return true;
	}

	private boolean checkEditUserName(Login login) {
		List<Login> loginList = getAllUserList();
		for (Login existingLogin : loginList) {
			if (existingLogin.getUserName().equalsIgnoreCase(login.getUserName())
					&& existingLogin.getUserId() == login.getUserId()) {
				return true;
			}

			if (existingLogin.getUserName().equalsIgnoreCase(login.getUserName())
					&& existingLogin.getUserId() != login.getUserId()) {
				return false;
			}

		}
		return true;

	}

	@Override
	public String getAllUser() {
		String userListJson = null;
		try {
			Map<String, Object> userMap = new HashMap<>();
			userMap.put("user", getAllUserList());
			userListJson = new ObjectMapper().writeValueAsString(userMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userListJson;

	}

	private List<Login> getAllUserList() {
		List<Login> loginList = null;
		try {
			CriteriaQuery<Login> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Login.class);
			criteriaQuery.select(criteriaQuery.from(Login.class));
			loginList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginList;
	}

	@Override
	public boolean editUser(Login login) {
		boolean isUpdated = false;
		try {

			if (checkEditUserName(login)) {
				entityManager.merge(login);
				isUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isUpdated;
	}

	@Override
	public boolean deleteUser(long userId) {
		boolean isDeleted = false;
		try {
			entityManager.remove(entityManager.getReference(Login.class, userId));
			isDeleted = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDeleted;
	}

}
