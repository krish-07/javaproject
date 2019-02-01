package com.pointel.poc.add;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointel.poc.db.entity.AudioExtension;
import com.pointel.poc.db.entity.AudioTable;
import com.pointel.poc.db.entity.Login;
import com.pointel.poc.db.entity.PocMenu;
import com.pointel.poc.filter.TraceQueryConstant;

@Transactional
@Repository
public class AddFilterRepositoryImpl implements AddFilterRepository {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private AddFilterService filterService;

	Login login = new Login();

	@SuppressWarnings("unchecked")
	@Override
	public boolean getaddFilteredResult(AudioTable audioTable) {
		boolean isInserted = false;
		try {

			entityManager.persist(audioTable);

			isInserted = true;
		} catch (Exception e) {
			e.printStackTrace();
			isInserted = false;
			return isInserted;
		}

		return isInserted;
	}

	@Override
	public boolean checkFileName(String fileName) {
		boolean isChecked = true;
		try {
			Query query = entityManager.createQuery(checkFileQuery(fileName));
			if (!fileName.equals("")) {
				query.setParameter("FILENAME", fileName);
			}
			List<AudioTable> filteredData = query.getResultList();
			if (!filteredData.isEmpty()) {
				isChecked = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isChecked;
	}

	private String checkFileQuery(String fileName) {
		StringBuilder queryBuilder = new StringBuilder("From AudioTable WHERE ");
		if (!fileName.equals("")) {
			queryBuilder.append("fileName = :FILENAME");
		}

		return queryBuilder.toString();
	}

	@Override
	public String getProjectName(long userId) {
		List<Object[]> projectPathConfigList = null;
		Map<String, Object> getProjectMap = null;
		List<String> getProjectName = null;
		List<String> getAudioType = null;
		List<PocMenu> getPocMenuList = null;
		ObjectMapper objMap = null;
		String jsonMap = null;
		String project = "";
		String extension = "";
		int count = 0;
		try {
			getProjectMap = new HashMap<>();
			getProjectName = new ArrayList<>();
			getAudioType = new ArrayList<>();
			objMap = new ObjectMapper();
			login.setUserId(userId);
			Query query = entityManager.createNativeQuery(TraceQueryConstant.GETINSERTPROJECTQUERY.getSqlQuery());
			if (userId != 0) {
				query.setParameter("USERID", userId);
			}
			if (!(query.getResultList().isEmpty())) {
				projectPathConfigList = query.getResultList();
				
				for (Object row : projectPathConfigList) {
					if (count == 0) {
						project = (String) row;
						
					} else {
						project=project+","+(String) row;
						
					}
					count++;
					getProjectMap.put("ProjectName", project);
				}
				
				
				/*for (Object[] row : projectPathConfigList) {
					if (count == 0) {
						project = (String) row[0];
						extension = (String) row[1];
						getProjectName.add(project);
						getAudioType.add(extension);
						
					} else {
						if ((!getProjectName.contains((String) row[0]))) {
							project = project + "," + (String) row[0];
							getProjectName.add((String) row[0]);
						}
						if (!getAudioType.contains((String) row[1])) {
							extension = extension + "," + (String) row[1];
							getAudioType.add((String) row[1]);
						}
						
					}
					count++;
					getProjectMap.put("ProjectName", project);
					getProjectMap.put("AudioType", extension);

				}*/
			}
			else{
				getProjectMap.put("ProjectName", null);
			}

			Query queryLang = entityManager.createQuery(TraceQueryConstant.GETINSERTLANGUAGEQUERY.getSqlQuery());
			if (userId != 0) {
				queryLang.setParameter("USERID", userId);
			}
			if (!(queryLang.getResultList().isEmpty())) {
				getPocMenuList = queryLang.getResultList();
				getProjectMap.put("Language", getPocMenuList.get(0).getLanguageMenu());
			}
			else{
				getProjectMap.put("Language", null);
			}

			if (!getProjectMap.isEmpty()) {
				jsonMap = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(getProjectMap);
			} 

			return jsonMap;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getAudioType(String projectName) {
		List<Object[]> getAudioTypeList = null;
		Map<String, String> getAudioTypeMap = null;
		List audioTypeList=null;
		ObjectMapper objMap = null;
		String jsonMap = null;
		String audioType = "";
		int count=0;
		try {
			audioTypeList=new ArrayList();
			Query query = entityManager.createNativeQuery(TraceQueryConstant.GETINSERTAUDIOTYPEQUERY.getSqlQuery());
			if (!projectName.equals("")) {
				query.setParameter("USERID", login.getUserId());
				query.setParameter("PROJECTNAME", projectName);
			}
			getAudioTypeMap = new HashMap<>();
			objMap = new ObjectMapper();

			if(!(query.getResultList().isEmpty())){
				getAudioTypeList=query.getResultList();
				for(Object row : getAudioTypeList){
					if(count==0){
						audioType=(String) row;
						audioTypeList.add(audioType);
					}
					else{
						if(!audioTypeList.contains((String) row)) {
						audioType=audioType+","+(String)row;
						audioTypeList.add((String)row);
						}
						
					}
					count++;
				}
			
			getAudioTypeMap.put("AudioType", audioType);
			}
			else{
				getAudioTypeMap.put("AudioType", null);
			}

			if (!getAudioTypeMap.isEmpty()) {
				jsonMap = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(getAudioTypeMap);
				return jsonMap;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getVoiceName(String language) {
		List<Object[]> getVoiceNameList = null;
		Map<String, String> getVoiceNameMap = null;
		ObjectMapper objMap = null;
		String jsonMap = null;
		String voiceName = "";
		int count = 0;

		try {
			Query query = entityManager.createQuery(TraceQueryConstant.GETINSERTVOICENAMEQUERY.getSqlQuery());
			if (!language.equals("")) {
				query.setParameter("USERID", login.getUserId());
				query.setParameter("LANGUAGE", language);
			}
			
			getVoiceNameMap = new HashMap<>();
			objMap = new ObjectMapper();
			if(!query.getResultList().isEmpty()){
				getVoiceNameList = query.getResultList();
		
			for (Object[] row : getVoiceNameList) {
				if (count == 0) {
					voiceName = (String) row[0];
					if (row[1].toString().trim().equalsIgnoreCase("Male")) {

						getVoiceNameMap.put("MaleVoiceName", (String) row[0]);
					} else {
						getVoiceNameMap.put("FemaleVoiceName", (String) row[0]);
					}

				} else {
					voiceName = voiceName + "," + (String) row[0];
					if (row[1].toString().trim().equalsIgnoreCase("Male")) {
						getVoiceNameMap.put("MaleVoiceName", (String) row[0]);
					} else {
						getVoiceNameMap.put("FemaleVoiceName", (String) row[0]);
					}
				}
				count++;
			}
			

			getVoiceNameMap.put("VoiceMenu", voiceName);
			}
			
			else{
				getVoiceNameMap.put("VoiceMenu",null);
			}

			if (!getVoiceNameMap.isEmpty()) {
				jsonMap = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(getVoiceNameMap);
				return jsonMap;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getAudioPath(String audioExtension, String projectName) {
		List<Object[]> getAudioPathList = null;
		List<Object> audioPathList=null;
		List<Long> projectAllocationIdList = null;
		Map<String, Object> getAudioPathMap = null;
		ObjectMapper objMap = null;
		String jsonMap = null;
		//String audioPath = "";
		String extensionPathId = "";
		int count = 0;
		try {
			Query queryAudioPath = entityManager
					.createQuery(TraceQueryConstant.GETINSERTAUDIOFILEPATHQUERY.getSqlQuery());
			if ((!audioExtension.isEmpty()) && (!projectName.isEmpty())) {
				queryAudioPath.setParameter("PROJECTNAME", projectName);
				queryAudioPath.setParameter("EXTENSIONTYPE", audioExtension);
			}

			 audioPathList = new ArrayList<>();
			getAudioPathMap = new HashMap<>();
			objMap = new ObjectMapper();
			if(!(queryAudioPath.getResultList().isEmpty())){
				getAudioPathList = queryAudioPath.getResultList();
			for (Object[] row : getAudioPathList) {
				Map<String, Object> audioPath = new HashMap<>();
				if((String) row[0]==null){
					getAudioPathMap.put("ExcelCheckAudioPath", null);
				}else{
				audioPath.put("audioPaths", (String) row[0]);
				audioPath.put("ExtensionID", String.valueOf(row[1]));
				audioPathList.add(audioPath);
				getAudioPathMap.put("ExcelCheckAudioPath",  (String) row[0]);
				}
			
			}
			getAudioPathMap.put("audioFilePath", audioPathList);

			
			}
			else{
				getAudioPathMap.put("audioFilePath", null);

			}
			
			Query queryProjectAllocationId = entityManager
					.createQuery(TraceQueryConstant.GETINSERTPROJECTALLOCATIONIDQUERY.getSqlQuery());
			if ((!projectName.isEmpty())) {
				queryProjectAllocationId.setParameter("PROJECTNAME", projectName);
				queryProjectAllocationId.setParameter("USERID", login.getUserId());
			}
			
			
			projectAllocationIdList = queryProjectAllocationId.getResultList();
			
			if(!(queryAudioPath.getResultList().isEmpty())){
			for (Long row : projectAllocationIdList) {
				getAudioPathMap.put("ProjectAllocationID", String.valueOf(row));
			}
			
			}
			else{
				getAudioPathMap.put("ProjectAllocationID", null);
			}

			jsonMap = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(getAudioPathMap);
			return jsonMap;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String checkDBFileName(List<String> addfilename, List<Map<String, String>> excelTotalValue,
			List<String> cellHeaderobj) {
		List<String> dbDuplicateFileName = null;
		ObjectMapper objMap = null;
		String jsonMap = null;
		try {
			StringBuilder queryBuilder = new StringBuilder("From AudioTable");
			Query query = entityManager.createQuery(queryBuilder.toString());
			List<AudioTable> dbFileName = query.getResultList();
			objMap = new ObjectMapper();
			dbDuplicateFileName = new ArrayList<>();

			for (int i = 0; i < dbFileName.size(); i++) {

				if (addfilename.toString().contains(dbFileName.get(i).getFileName().toLowerCase())) {

					for (String addfile : addfilename) {
						if (addfile.equalsIgnoreCase(dbFileName.get(i).getFileName())) {
							dbDuplicateFileName.add(dbFileName.get(i).getFileName());
						}
					}
				}

			}
			if (!dbDuplicateFileName.isEmpty()) {
				jsonMap = objMap.writerWithDefaultPrettyPrinter()
						.writeValueAsString("<b>Duplicate Filename in DB:</b>  " + dbDuplicateFileName);

				return jsonMap;
			} else {
				return filterService.updateValuePojo(excelTotalValue, cellHeaderobj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateExcelInDB(List<AudioTable> audioTableObj) {

		ObjectMapper objMap = new ObjectMapper();
		String insert = null;
		String jsonMap = null;
		try {
			if (!audioTableObj.isEmpty()) {
				for (int i = 0; i < audioTableObj.size(); i++) {
					entityManager.persist(audioTableObj.get(i));
				}
				insert = "ExcelFile data is inserted successfully";

			} else {
				insert = "ExcelFile data isn't inserted";
			}
			
			
			jsonMap = objMap.writeValueAsString(insert);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonMap;
	}
 
	public int updateAudioTable(AudioTable audioTable) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		int result = 0;

		try {
			if (audioTable.getCreatedDateTime() == null || audioTable.getLastModifiedDateTime() == null) {
				System.out.println("failure");
				Query query = entityManager
						.createNativeQuery("Update audioTable set audioStatus='Failure' where filename = :FILENAME");
				query.setParameter("FILENAME", audioTable.getFileName());
				result = query.executeUpdate();
				System.out.println("failure update");
			} else {
				Query query = entityManager
						.createNativeQuery(TraceQueryConstant.GETUPDATEAUDIOTABLEQUERY.getSqlQuery());
				query.setParameter("CREATEDATE", audioTable.getCreatedDateTime());
				query.setParameter("LASTMODIFIEDDATETIME", audioTable.getLastModifiedDateTime());
				query.setParameter("FILENAME", audioTable.getFileName());
				result = query.executeUpdate();
				System.out.println("success update update");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
}
