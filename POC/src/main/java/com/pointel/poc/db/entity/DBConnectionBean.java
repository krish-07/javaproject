package com.pointel.poc.db.entity;


public class DBConnectionBean {
	private String userPassword;
	private String hostName;
	private String userName;
	private String databaseType;
	private String databaseName;
	private String databaseDriverClass;
	private String databaseUrl;
	private int portNumber;
	

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabaseDriverClass() {
		return databaseDriverClass;
	}

	public void setDatabaseDriverClass(String databaseDriverClass) {
		this.databaseDriverClass = databaseDriverClass;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	@Override
	public String toString() {
		return "DBConnectionBean [userPassword=" + userPassword + ", hostName=" + hostName + ", userName=" + userName
				+ ", databaseType=" + databaseType + ", databaseName=" + databaseName + ", databaseDriverClass="
				+ databaseDriverClass + ", databaseUrl=" + databaseUrl + ", portNumber=" + portNumber + "]";
	}
}

