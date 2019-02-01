package com.pointel.poc.login;

public enum LoginConstants {
	
	POCUSERID("POCUSERID"),
	POCUSERROLE("POCUSERROLE");
	
	

	private String key;

	private LoginConstants(String key) {
		this.key = key;
	}

	public String getSqlQuery() {
		return key;
	}

}
