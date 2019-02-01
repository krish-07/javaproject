package com.pointel.poc.db.entity;

public class WebserviceResponseBean {
	
	private int returnCode;
	private String errorMessage;
	private String fileName;
	private String createdTime;
	private String modifiedTime;
	private String audioType;
	private String gender;
	private String language;
	private String voiceName;
	private String audioInput;
	private String projectName;
	private String audioFilePath;
	private AFPathConfig extensionPathId;
	private ProjectAllocation projectAllocationId;
	private int fileStatus;
	
	
	
	
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public int getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(int fileStatus) {
		this.fileStatus = fileStatus;
	}
	public AFPathConfig getExtensionPathId() {
		return extensionPathId;
	}
	public void setExtensionPathId(AFPathConfig extensionPathId) {
		this.extensionPathId = extensionPathId;
	}
	public ProjectAllocation getProjectAllocationId() {
		return projectAllocationId;
	}
	public void setProjectAllocationId(ProjectAllocation projectAllocationId) {
		this.projectAllocationId = projectAllocationId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getAudioFilePath() {
		return audioFilePath;
	}
	public void setAudioFilePath(String audioFilePath) {
		this.audioFilePath = audioFilePath;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getAudioType() {
		return audioType;
	}
	public void setAudioType(String audioType) {
		this.audioType = audioType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getVoiceName() {
		return voiceName;
	}
	public void setVoiceName(String voiceName) {
		this.voiceName = voiceName;
	}
	public String getAudioInput() {
		return audioInput;
	}
	public void setAudioInput(String audioInput) {
		this.audioInput = audioInput;
	}
	@Override
	public String toString() {
		return "WebserviceResponseBean [returnCode=" + returnCode + ", errorMessage=" + errorMessage + ", fileName="
				+ fileName + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", audioType="
				+ audioType + ", gender=" + gender + ", language=" + language + ", voiceName=" + voiceName
				+ ", audioInput=" + audioInput + ", projectName=" + projectName + ", audioFilePath=" + audioFilePath
				+ ", extensionPathId=" + extensionPathId + ", projectAllocationId=" + projectAllocationId
				+ ", fileStatus=" + fileStatus + "]";
	}
	
	
	
	
	

}
