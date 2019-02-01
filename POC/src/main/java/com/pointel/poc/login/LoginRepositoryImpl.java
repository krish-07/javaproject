package com.pointel.poc.login;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pointel.poc.db.entity.Login;

@Transactional
@Repository
public class LoginRepositoryImpl implements LoginRepository {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Login validateUser(String identityName, String watchWord) {
		Login login = null;
		try {
			Query query = entityManager.createQuery("From Login Where USERNAME= :USERNAME");
			query.setParameter("USERNAME", identityName);
			List<Login> loginList = query.getResultList();
			if (!loginList.isEmpty()) {
				login = loginList.get(0);
				if (login.getUserName().equalsIgnoreCase(identityName) && login.getPassword().equals(watchWord)) {
					if (login.getActive().equals("1")) {
						login.setComment("555");
					} else {
						login.setComment("444");
					}
				} else {
					login.setComment("333");
				}
			}else {
				login = new Login();
				login.setComment("333");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return login;
	}

}
