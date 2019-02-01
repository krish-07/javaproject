package com.pointel.poc.filter;

public enum TraceQueryConstant {

	/*// insert
	GETPROJECTQUERY("select project.projectName,audioextension.extensionType FROM Project project JOIN  project.afPathConfig afpath JOIN afpath.audioExtension audioextension  "
			+ "WHERE project.projectId IN (select project.projectId from ProjectAllocation al where login.userId = :USERID)"),*/
	
GETINSERTPROJECTQUERY("SELECT PROJECT.PROJECTNAME FROM PROJECT WHERE PROJECT.PROJECT_ID IN(SELECT PROJECT_ID FROM PROJECTALLOCATION WHERE USER_ID = :USERID)"),
	
	GETPROJECTQUERY("SELECT PROJECT.PROJECTNAME,AUDIOEXTENSION.EXTENSIONTYPE FROM PROJECT "
	  +"INNER JOIN AFPATHCONFIG ON PROJECT.PROJECT_ID = AFPATHCONFIG.PROJECT_ID "
	  +"INNER JOIN AUDIOEXTENSION ON AUDIOEXTENSION.EXTENSION_ID = "
	 +"AFPATHCONFIG.EXTENSION_ID "
	  +" WHERE PROJECT.PROJECT_ID IN(SELECT PROJECT_ID FROM PROJECTALLOCATION WHERE USER_ID = :USERID)"),
	
	GETINSERTAUDIOTYPEQUERY("SELECT AUDIOEXTENSION.EXTENSIONTYPE FROM PROJECT INNER JOIN AFPATHCONFIG ON PROJECT.PROJECT_ID = AFPATHCONFIG.PROJECT_ID INNER JOIN AUDIOEXTENSION ON AUDIOEXTENSION.EXTENSION_ID = AFPATHCONFIG.EXTENSION_ID  "
		     +"WHERE PROJECT.PROJECT_ID IN(SELECT PROJECT_ID FROM PROJECTALLOCATION WHERE USER_ID = :USERID) AND PROJECT.PROJECTNAME= :PROJECTNAME"),
	
	GETINSERTLANGUAGEQUERY(" From PocMenu pocmenu WHERE pocmenu.serviceId IN(select pocMenu.serviceId from ProjectAllocation where login.userId = :USERID)"), 
	GETINSERTVOICENAMEQUERY("select voiceconfig.voiceTypeConfig,voiceconfig.genderConfig from VoiceConfig voiceconfig where voiceconfig.pocMenu.serviceId In(select projectallocate.pocMenu.serviceId from ProjectAllocation projectallocate where projectallocate.login.userId = :USERID) and voiceconfig.languageConfig= :LANGUAGE"), 
	GETINSERTAUDIOFILEPATHQUERY("select afpath.extensionType,afpath.id FROM AFPathConfig afpath JOIN afpath.project p  JOIN afpath.audioExtension audioextension WHERE p.projectName = :PROJECTNAME and audioextension.extensionType = :EXTENSIONTYPE"), 
	GETINSERTPROJECTALLOCATIONIDQUERY("select projectallocate.id From ProjectAllocation projectallocate join projectallocate.project p where p.projectName = :PROJECTNAME and projectallocate.login.userId= :USERID"),

	// updatesingleaudio
	GETUPDATEAUDIOTABLEQUERY("Update audioTable set createdDateTime = :CREATEDATE,lastModifiedDateTime = :LASTMODIFIEDDATETIME,audioStatus = 'Success' where filename = :FILENAME"),
	/*GETUPDATEAUDIOTYPEQUERY("select audioextension.extensionType FROM Project project JOIN  project.afPathConfig afpath JOIN afpath.audioExtension audioextension  "
			+ "WHERE project.projectId IN (select project.projectId from ProjectAllocation al where login.userId = :USERID) and project.fileName= :"),*/
	GETVOICECONFIGQUERY("SELECT Language_Config,VoiceType_Config,Gender_Config  FROM VOICECONFIG WHERE SERVICE_ID IN(SELECT SERVICE_ID FROM PROJECTALLOCATION WHERE USER_ID = :POCUSERID)"), 
	/*GETAUDIOFILEPATHQUERY("SELECT AFPathConfig.Extension_ID,AFPathConfig.ExtensionPath FROM AFPathConfig INNER JOIN PROJECT ON PROJECT.PROJECT_ID = AFPATHCONFIG.PROJECT_ID INNER JOIN AudioExtension ON "
						+ " AFPATHCONFIG.Extension_ID = AudioExtension.Extension_ID INNER JOIN PROJECTALLOCATION ON PROJECT.PROJECT_ID = PROJECTALLOCATION.PROJECT_ID"
						+ " WHERE PROJECT.ProjectName = :PROJECTNAME and PROJECTALLOCATION.USER_ID = :POCUSERID"), */
	GETTRACEQUERY("SELECT audio.AUDIO_ID,audio.FILENAME,audio.AUDIOTYPE,audio.GENDER,audio.LANGUAGE, audio.VOICENAME," + 
			"audio.AUDIOINPUT,audio.CREATEDDATETIME, audio.LASTMODIFIEDDATETIME,audio.AUDIOFILEPATH,audio.AUDIOSTATUS," + 
			"PROJECT.PROJECTNAME,PROJECT.HOSTNAME,audio.ProjectAllocate_ID,audio.ExtensionPath_ID,Login.UserName FROM AUDIOTABLE audio \r\n" + 
			"INNER JOIN PROJECTALLOCATION  ON audio.PROJECTALLOCATE_ID =  PROJECTALLOCATION.PROJECTALLOCATE_ID " + 
			"INNER JOIN Login on PROJECTALLOCATION.USER_ID = Login.USER_ID " + 
			"INNER JOIN PROJECT on PROJECTALLOCATION.PROJECT_ID = PROJECT.PROJECT_ID WHERE  ");
	private String queryString;

	private TraceQueryConstant(String queryString) {
		this.queryString = queryString;
	}

	public String getSqlQuery() {
		return queryString;
	}

}
