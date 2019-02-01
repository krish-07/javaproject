package com.pointel.poc.project.map;

public enum MapProjectQueryConstant {

	GETPROJECTMAPPINGQUERY(
			 " SELECT PROJECT.PROJECT_ID,PROJECT.PROJECTNAME,MENU.SERVICE_ID,MENU.APISERVICENAME FROM PROJECTALLOCATION "
		   + "  ALLOCATION INNER JOIN PROJECT ON PROJECT.PROJECT_ID=ALLOCATION.PROJECT_ID INNER JOIN  "  
		   + "  POCMENU MENU ON ALLOCATION.SERVICE_ID = MENU.SERVICE_ID WHERE ALLOCATION.USER_ID = :USERID");
	
	

	private String queryString;

	private MapProjectQueryConstant(String queryString) {
		this.queryString = queryString;
	}

	public String getSqlQuery() {
		return queryString;
	}

}
