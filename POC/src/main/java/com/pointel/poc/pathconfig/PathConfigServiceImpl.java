package com.pointel.poc.pathconfig;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointel.poc.db.entity.PathCheckBean;

@Service
public class PathConfigServiceImpl implements PathConfigService  {

	/*@Autowired
	private PathConfigRepository pathConfigRepository;*/
	
	@Override
	public String loadPathValues() {
		ObjectMapper jsonobj = new ObjectMapper();
		try {
			Map<String, String> map = new HashMap<>();
			ResourceBundle bundle = ResourceBundle.getBundle("DB");
			map.put("ExcelFilePath", bundle.getString("ExcelFilePath"));
			map.put("CreatedAudioFilePath", bundle.getString("CreatedAudioFilePath"));
			return jsonobj.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public boolean checkPath(PathCheckBean pathCheck) {
		boolean isCreated=false;
		File file=null;
		try{
			file=new File(pathCheck.getExcelFilePath());
			if(file.exists()){
				isCreated=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return isCreated;
	}
	
	@Override
	public boolean checkAudioPath(PathCheckBean checkAudioPath) {
		boolean isAudioCreated=false;
		File file=null;
		try{
			file=new File(checkAudioPath.getAudioCreatedFilePath());
			if(file.exists()){
				isAudioCreated=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return isAudioCreated;
		
	}
	
	@Override
	public boolean changeConfigPath(PathCheckBean pathCheck) {
		boolean checkConfigPath = false;
		Path path = Paths.get("src/main/resources/DB.properties");
		Charset charset = Charset.forName("US-ASCII");
		ArrayList<String> updateFile = new ArrayList<>();
		boolean lineSkip = true;
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			if (checkPath(pathCheck)) {
				String line = null;
				updateFile.add("#File Modified At:" + new Date());
				while ((line = reader.readLine()) != null) {
					String[] splitComment = line.split(":");
					if (splitComment[0].equals("#File Modified At"))
						lineSkip = false;
					String[] splitLine = line.split("=");
					if (splitLine[0].equalsIgnoreCase("ExcelFilePath"))
						line = "ExcelFilePath" + "=" + pathCheck.getExcelFilePath().trim();
					if (splitLine[0].equalsIgnoreCase("CreatedAudioFilePath"))
						line = "CreatedAudioFilePath" + "=" + pathCheck.getAudioCreatedFilePath().trim();
					if (lineSkip) {
						updateFile.add(line);
					}
					lineSkip = true;
				}
				Files.write(path, updateFile, Charset.forName("US-ASCII"));
				checkConfigPath = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return checkConfigPath;
		
	}


	
}
