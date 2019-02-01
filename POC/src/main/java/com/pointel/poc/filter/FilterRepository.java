package com.pointel.poc.filter;

import java.util.Date;

import com.pointel.poc.db.entity.AudioTable;

public interface FilterRepository {

	String getFilteredResult(AudioTable audioTable,String pocUserId);

	boolean deleteAudioRecord(String audioId);
	
	boolean getUpdateFilteredResult(AudioTable audioTable, String extensionId);

	String onLoadProp(String pocUserId);

	String getAudioFilePathRepository(String pocUserId, String projectName, String audioType);

	Date getAudioCreatedDate(String fileName);

	boolean updateAudioStatus(AudioTable audioTable);


}