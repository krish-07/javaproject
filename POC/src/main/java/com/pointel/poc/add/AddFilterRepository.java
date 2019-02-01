package com.pointel.poc.add;

import java.util.List;
import java.util.Map;

import com.pointel.poc.db.entity.AudioTable;

public interface AddFilterRepository {

	boolean getaddFilteredResult(AudioTable audioTable);

	boolean checkFileName(String fileName);

	String checkDBFileName(List<String> addfilename, List<Map<String, String>> excelTotalValue,
			List<String> cellHeaderobj);

	String updateExcelInDB(List<AudioTable> audioTableObj);

	String getVoiceName(String language);

	String getAudioPath(String audioExtension, String projectName);

	String getProjectName(long userId);

	int updateAudioTable(AudioTable audioTable);

	String getAudioType(String projectName);

}
