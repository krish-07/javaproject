package com.pointel.poc.filter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointel.poc.db.entity.AFPathConfig;
import com.pointel.poc.db.entity.AudioTable;
import com.pointel.poc.db.entity.ProjectAllocation;

@Transactional
@Repository
public class FilterRepositoryImpl implements FilterRepository {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public String getFilteredResult(AudioTable audioTable, String pocUserId) {

		try {
			Query query = entityManager.createNativeQuery(queryMaker(audioTable));

			if (!audioTable.getFileName().equals("")) {
				query.setParameter("FILENAME", audioTable.getFileName());
			}

			if (!audioTable.getAudioType().equals("")) {
				query.setParameter("AUDIOTYPE", audioTable.getAudioType());
			}

			if (!audioTable.getVoiceName().equals("")) {
				query.setParameter("VOICENAME", audioTable.getVoiceName());
			}

			if (!audioTable.getGender().equals("")) {
				query.setParameter("GENDER", audioTable.getGender());
			}
			if (!audioTable.getLanguage().equals("")) {
				query.setParameter("LANGUAGE", audioTable.getLanguage());
			}
			if (!audioTable.getProjectName().equals("")) {
				query.setParameter("PROJECTNAME", audioTable.getProjectName());
			}
			List<Object[]> filteredData = query.getResultList();
			List<AudioTable> audiPropList = new ArrayList<>();
			for (Object[] audioDetail : filteredData) {
				AudioTable audioProp = new AudioTable();
				audioProp.setAudioId(Long.parseLong(audioDetail[0].toString()));
				audioProp.setFileName(audioDetail[1].toString());
				audioProp.setAudioType(audioDetail[2].toString());
				audioProp.setGender(audioDetail[3].toString());
				audioProp.setLanguage(audioDetail[4].toString());
				audioProp.setVoiceName(audioDetail[5].toString());
				audioProp.setAudioInput(audioDetail[6].toString());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				audioProp.setCreatedDateTime(
						audioDetail[7] == null ? null : simpleDateFormat.parse(audioDetail[7].toString()));
				audioProp.setLastModifiedDateTime(
						audioDetail[8] == null ? null : simpleDateFormat.parse(audioDetail[8].toString()));
				audioProp.setAudioFilePath(audioDetail[9].toString());
				audioProp.setAudioStatus(audioDetail[10] == null ? "-" : audioDetail[10].toString());
				audioProp.setProjectName(audioDetail[11].toString());
				audioProp.setHostName(audioDetail[12].toString());
				audioProp.setProjectAllocateId(audioDetail[13].toString());
				audioProp.setaFPathConfigId(audioDetail[14].toString());
				audioProp.setUserId(audioDetail[15].toString());
				audiPropList.add(audioProp);
			}

			return new ObjectMapper().writeValueAsString(audiPropList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private String queryMaker(AudioTable audioTable) {
		String filterQuery = null;
		StringBuilder queryBuilder = new StringBuilder(TraceQueryConstant.GETTRACEQUERY.getSqlQuery());

		if (!audioTable.getFileName().equals("")) {
			queryBuilder.append("audio.fileName = :FILENAME AND ");
		}

		if (!audioTable.getAudioType().equals("")) {
			queryBuilder.append("audio.audioType = :AUDIOTYPE AND ");
		}

		if (!audioTable.getVoiceName().equals("")) {
			queryBuilder.append("audio.voiceName = :VOICENAME AND ");
		}

		if (!audioTable.getGender().equals("")) {
			queryBuilder.append("audio.gender = :GENDER AND ");
		}
		if (!audioTable.getLanguage().equals("")) {
			queryBuilder.append("audio.language = :LANGUAGE AND ");
		}
		if (!audioTable.getProjectName().equals("")) {
			queryBuilder.append("PROJECT.PROJECTNAME = :PROJECTNAME AND");
		}

		filterQuery = queryBuilder.toString().substring(0, queryBuilder.lastIndexOf("AND") - 1);
		return filterQuery;
	}

	@Override
	public boolean deleteAudioRecord(String audioId) {
		boolean isDeleted = false;
		try {
			AudioTable audioTable = entityManager.getReference(AudioTable.class, Long.parseLong(audioId));
			entityManager.remove(audioTable);
			isDeleted = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	@Override
	public boolean getUpdateFilteredResult(AudioTable audioTable,String extensionId) {
		boolean isUpdated = false;
		try {
			audioTable.setaFPathConfig(
					entityManager.getReference(AFPathConfig.class, Long.parseLong(extensionId)));
			audioTable.setProjectAllocation(entityManager.getReference(ProjectAllocation.class,
					Long.parseLong(audioTable.getProjectAllocateId())));
			audioTable.setAudioStatus("InProgress");
			entityManager.merge(audioTable);
			isUpdated = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return isUpdated;

	}
	
	public boolean updateAudioStatus(AudioTable audioTable) {
		boolean isUpdated = false;
		
		Query query = entityManager
				.createNativeQuery("Update audioTable set audioStatus= :AUDIOSTATUS,CreatedDateTime= :CREATEDDATETIME,LastModifiedDateTime= :LASTMODIFIEDTIME where filename = :FILENAME");
		query.setParameter("AUDIOSTATUS", audioTable.getAudioStatus());
		query.setParameter("FILENAME", audioTable.getFileName());
		query.setParameter("CREATEDDATETIME", audioTable.getCreatedDateTime());
		query.setParameter("LASTMODIFIEDTIME", audioTable.getLastModifiedDateTime());
		if(query.executeUpdate()>0)
			isUpdated = true;
		else
			isUpdated = false;
		
		return isUpdated;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String onLoadProp(String pocUserId) {
		String userListJson = null;
		try {
			Map<String, Object> traceConfig = new HashMap<>();

			Query getprojectQuery = entityManager.createNativeQuery(TraceQueryConstant.GETPROJECTQUERY.getSqlQuery());
			getprojectQuery.setParameter("USERID", Long.valueOf(pocUserId));
			List<Object[]> projectName = getprojectQuery.getResultList();

			List<Object> projectList = new ArrayList<>();

			for (Object[] loadProp : projectName) {
				Map<String, Object> project = new HashMap<>();
				project.put("ProjectName", loadProp[0]);
				
				project.put("ExtensionType", loadProp[1]);
				projectList.add(project);
			}

			List<Object> configList = new ArrayList<>();
			Query getConfigQuery = entityManager
					.createNativeQuery(TraceQueryConstant.GETVOICECONFIGQUERY.getSqlQuery());
			getConfigQuery.setParameter("POCUSERID", pocUserId);
			List<Object[]> voiceConfigList = getConfigQuery.getResultList();
			for (Object[] loadProp : voiceConfigList) {
				Map<String, Object> voiceConfig = new HashMap<>();
				voiceConfig.put("Language", loadProp[0]);
				voiceConfig.put("VoiceType", loadProp[1]);
				voiceConfig.put("Gender", loadProp[2]);
				configList.add(voiceConfig);
			}
			
			
			traceConfig.put("project", projectList);
			traceConfig.put("voiceConfig", configList);

			userListJson = new ObjectMapper().writeValueAsString(traceConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userListJson;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAudioFilePathRepository(String pocUserId, String projectName, String audioType) {
		String audioPathListJson = null;
		try {
			Map<String, Object> traceConfig = new HashMap<>();

			Query getAudioPathQuery = entityManager.createQuery(TraceQueryConstant.GETINSERTAUDIOFILEPATHQUERY.getSqlQuery());			
			getAudioPathQuery.setParameter("PROJECTNAME", projectName);
	 		getAudioPathQuery.setParameter("EXTENSIONTYPE", audioType);
			List<Object[]> audioFilePath = getAudioPathQuery.getResultList();

			List<Object> audioPathList = new ArrayList<>();

			for (Object[] loadAudioPath : audioFilePath) {
				Map<String, Object> audioPath = new HashMap<>();
				audioPath.put("audioFilePath", loadAudioPath[0].toString());
				audioPath.put("ExtensionID", loadAudioPath[1].toString());
				audioPathList.add(audioPath);
			}			
			
			traceConfig.put("audioFilePath", audioPathList);

			audioPathListJson = new ObjectMapper().writeValueAsString(traceConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return audioPathListJson;
	}

	public Date getAudioCreatedDate(String fileName) {
		Date createdDate=null;
		System.out.println(fileName);
        System.out.println(fileName.subSequence(0, fileName.lastIndexOf(".")));
		try {
			Query query = entityManager.createNativeQuery("Select CreatedDateTime from AudioTable where FileName= :FILENAME");
			query.setParameter("FILENAME", fileName.subSequence(0, fileName.lastIndexOf(".")));
			createdDate=(Date) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(createdDate);
		return createdDate;
	}
}
