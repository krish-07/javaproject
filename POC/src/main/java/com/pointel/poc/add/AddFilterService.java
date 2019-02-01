package com.pointel.poc.add;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import com.pointel.poc.db.entity.AudioTable;
import com.pointel.poc.db.entity.WebserviceResponseBean;

public interface AddFilterService {

	boolean getaddFilteredResult(AudioTable audioTable);

	boolean checkFileName(String fileName);

	String uploadExcelFile(Part excelFilePath);

	String updateValuePojo(List<Map<String, String>> excelTotalValue, List<String> cellHeaderobj);

	List<AudioTable> findExcelFile(Part excelFilePath);

	//String checkAudioFile(List<AudioTable> fileNameList);

	int checkSingleAudioFile(AudioTable audioTable);

	String getVoiceName(String language);

	String getAudioPath(String audioExtension, String projectName);

	String getProjectName(long userId);

	List<WebserviceResponseBean> sendMultiAudio(List<AudioTable> res);

	String getAudioType(String projectName);

	


}
