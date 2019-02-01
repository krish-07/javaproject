package com.pointel.poc.db.entity;



public class PathCheckBean {
	
private String excelFilePath;
private String audioCreatedFilePath;
private String audioType;
private String audioFilePath;


public String getAudioType() {
	return audioType;
}

public void setAudioType(String audioType) {
	this.audioType = audioType;
}

public String getAudioFilePath() {
	return audioFilePath;
}

public void setAudioFilePath(String audioFilePath) {
	this.audioFilePath = audioFilePath;
}



public String getExcelFilePath() {
	return excelFilePath;
}

public void setExcelFilePath(String excelFilePath) {
	this.excelFilePath = excelFilePath;
}

public String getAudioCreatedFilePath() {
	return audioCreatedFilePath;
}

public void setAudioCreatedFilePath(String audioCreatedFilePath) {
	this.audioCreatedFilePath = audioCreatedFilePath;
}

}
