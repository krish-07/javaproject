package com.pointel.poc.filter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pointel.poc.db.entity.AudioTable;
import com.pointel.poc.db.entity.WebserviceResponseBean;

@Service
public class FilterServiceImpl implements FilterService {

	@Autowired
	private FilterRepository filterRepository;
	
	ResourceBundle bundle = ResourceBundle.getBundle("DB");
	
	@Override
	public String getFilteredResult(AudioTable audioTable,String pocUserId) {
		return filterRepository.getFilteredResult(audioTable,pocUserId);
	}

	@Override
	public boolean deleteAudioRecord(String audioId) {
		return filterRepository.deleteAudioRecord(audioId);
	}

	public byte[] readAudio(String fileName) {
		byte[] audioByte = null;
		try {
			
			File audioFilePlay = new File("\\\\192.168.1.117\\POC\\Prompts\\"+fileName);
			if(audioFilePlay.isFile()){
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				try (InputStream is = new FileInputStream(audioFilePlay)) {
					int nRead;
					byte[] data = new byte[16384];

					while ((nRead = is.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					audioByte = buffer.toByteArray();
				}
			}
		} catch (Exception e) {
			audioByte = new byte[0];
			e.printStackTrace();
		}
		return audioByte;
	}

	@Override
	public boolean getUpdateFilteredResult(AudioTable audioTable,String extensionPathId) {
		return filterRepository.getUpdateFilteredResult(audioTable,extensionPathId);
	}
	
	@Override
	public boolean updateAudio(AudioTable audioTable) {
			
			boolean result=false;
			List<AudioTable> reqBody=new ArrayList<>();
			reqBody.add(audioTable);
			try {
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<WebserviceResponseBean[]> response = restTemplate.postForEntity(bundle.getString("APISinglAudiourl"),reqBody,
						WebserviceResponseBean[].class);
				if (response.getBody()[0].getFileStatus() == 1) {
					SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					audioTable.setLastModifiedDateTime(sdf.parse(response.getBody()[0].getModifiedTime()));
					audioTable.setCreatedDateTime(sdf.parse(response.getBody()[0].getCreatedTime()));
					audioTable.setAudioStatus("Success");
					filterRepository.updateAudioStatus(audioTable);
					result=true;
					
				}else {
					audioTable.setAudioStatus("Failure");
					filterRepository.updateAudioStatus(audioTable);
					result=false;
				}
			}catch(Exception e) {
				System.out.println(e);
				audioTable.setAudioStatus("Failure");
				filterRepository.updateAudioStatus(audioTable);
				result=false;
			}
			return result;
		}
	
	@Override
	public boolean checkFileExist(String fileName) {
		boolean isExist = false;
		File audioFilePlay = new File("\\\\192.168.1.117\\POC\\Prompts\\"+fileName);
		if(audioFilePlay.isFile()){
			isExist = true;
		}
		return isExist;
	}
	
	
	@Override
	public String onLoadProp(String pocUserId) {
		return filterRepository.onLoadProp(pocUserId);
	}

	@Override
	public String getAudioFilePathService(String pocUserId,String projectName, String audioType) {
		return filterRepository.getAudioFilePathRepository(pocUserId,projectName,audioType);
	}

}
