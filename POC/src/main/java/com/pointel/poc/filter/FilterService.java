package com.pointel.poc.filter;

import com.pointel.poc.db.entity.AudioTable;


public interface FilterService {

	String getFilteredResult(AudioTable audioTable, String pocUserId);

	boolean getUpdateFilteredResult(AudioTable audioTable, String extensionPathId);

	boolean deleteAudioRecord(String audioId);

	public byte[] readAudio(String fileName);

	boolean checkFileExist(String fileName);


	String onLoadProp(String string);

	String getAudioFilePathService(String pocUserId,String projectName, String audioType);

	boolean updateAudio(AudioTable audioTable);





}
