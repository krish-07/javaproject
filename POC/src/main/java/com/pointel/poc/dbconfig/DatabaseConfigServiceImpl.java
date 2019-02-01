package com.pointel.poc.dbconfig;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointel.poc.db.entity.DBConnectionBean;

@Service
public class DatabaseConfigServiceImpl implements DatabaseConfigService {
	private static final String DATABASE_USERNAME="UserName";
	private static final String DATABASE_PASS="Password";
	private static final String DATABASE_NAME="Database";
	private static final String DATABASE_PORT="Port";
	private static final String DATABASE_HOSTADDRESS="HostName";
	
	@Override
	public String loadDBValues() {
		ObjectMapper jsonobj = new ObjectMapper();
		try {
			Map<String, String> map = new HashMap<>();
			ResourceBundle bundle = ResourceBundle.getBundle("DB");
			map.put(DATABASE_USERNAME, bundle.getString(DATABASE_USERNAME));
			map.put(DATABASE_PASS, bundle.getString(DATABASE_PASS));
			map.put(DATABASE_NAME, bundle.getString(DATABASE_NAME));
			map.put(DATABASE_PORT, bundle.getString(DATABASE_PORT));
			map.put(DATABASE_HOSTADDRESS, bundle.getString(DATABASE_HOSTADDRESS));
			return jsonobj.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public boolean checkConnection(DBConnectionBean dbConnection) {
		DriverManagerDataSource dataSourceBuilder = new DriverManagerDataSource();
		boolean isChecked = true;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("DB");
			dataSourceBuilder.setUrl(bundle.getString("DatabaseUrl")+dbConnection.getHostName()+":"+dbConnection.getPortNumber()+";databaseName="+dbConnection.getDatabaseName());
			isChecked = dataSourceBuilder.getConnection(dbConnection.getUserName(), dbConnection.getUserPassword()).isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isChecked;
	}

	@Override
	public boolean changeConfigDB(DBConnectionBean dbConnection) {
		boolean checkConnection = false;
		Path path = Paths.get("src/main/resources/DB.properties");
		Charset charset = Charset.forName("US-ASCII");
		ArrayList<String> updateFile = new ArrayList<>();
		boolean lineSkip = true;
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			if (!checkConnection(dbConnection)) {
				String line = null;
				updateFile.add("#File Modified At:" + new Date());
				while ((line = reader.readLine()) != null) {
					String[] splitComment = line.split(":");
					if (splitComment[0].equals("#File Modified At"))
						lineSkip = false;
					String[] splitLine = line.split("=");
					if (splitLine[0].equalsIgnoreCase(DATABASE_USERNAME))
						line = DATABASE_USERNAME + "=" + dbConnection.getUserName().trim();
					if (splitLine[0].equalsIgnoreCase(DATABASE_PASS))
						line = DATABASE_PASS + "=" + dbConnection.getUserPassword().trim();
					if (splitLine[0].equalsIgnoreCase(DATABASE_PORT))
						line = DATABASE_PORT + "=" + String.valueOf(dbConnection.getPortNumber()).trim();
					if (splitLine[0].equalsIgnoreCase(DATABASE_HOSTADDRESS))
						line = DATABASE_HOSTADDRESS + "=" + dbConnection.getHostName().trim();
					if (splitLine[0].equalsIgnoreCase(DATABASE_NAME))
						line = DATABASE_NAME + "=" + dbConnection.getDatabaseName().trim();
					if (lineSkip) {
						updateFile.add(line);
					}
					lineSkip = true;
				}
				Files.write(path, updateFile, Charset.forName("US-ASCII"));

				checkConnection = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return checkConnection;
	}

}
