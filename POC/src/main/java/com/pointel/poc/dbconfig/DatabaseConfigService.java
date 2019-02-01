package com.pointel.poc.dbconfig;

import com.pointel.poc.db.entity.DBConnectionBean;

public interface DatabaseConfigService {

	String loadDBValues();

	boolean changeConfigDB(DBConnectionBean dbConnection);
	boolean checkConnection(DBConnectionBean dbConnection);
}
