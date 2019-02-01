package com.pointel.poc.add;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointel.poc.db.entity.AFPathConfig;
import com.pointel.poc.db.entity.AudioTable;
import com.pointel.poc.db.entity.ProjectAllocation;
import com.pointel.poc.db.entity.WebserviceResponseBean;

@Service
public class AddFilterServiceImpl implements AddFilterService {

	ResourceBundle bundle = ResourceBundle.getBundle("DB");
	@Autowired
	private AddFilterRepository filterRepository;

	@Override
	public boolean getaddFilteredResult(AudioTable audioTable) {

		return filterRepository.getaddFilteredResult(audioTable);
	}

	@Override
	public boolean checkFileName(String fileName) {

		return filterRepository.checkFileName(fileName);
	}

	@Override
	public String getProjectName(long userId) {
		return filterRepository.getProjectName(userId);
	}

	@Override
	public String getAudioType(String projectName) {
		return filterRepository.getAudioType(projectName);
	}

	@Override
	public String getVoiceName(String language) {
		return filterRepository.getVoiceName(language);
	}

	@Override
	public String getAudioPath(String audioExtension, String projectName) {
		return filterRepository.getAudioPath(audioExtension, projectName);
	}

	@Override
	public String uploadExcelFile(Part uploadExcelFile) {
		FileOutputStream out = null;
		InputStream filecontent = null;
		String uploadFilePath = null;
		File uploadFileExcel = null;

		try {
			String UPLOADED_FOLDER = bundle.getString("ExcelFilePath");
			uploadFilePath = UPLOADED_FOLDER + '/' + uploadExcelFile.getSubmittedFileName();
			uploadFileExcel = new File(uploadFilePath);

			out = new FileOutputStream(uploadFileExcel);

			filecontent = uploadExcelFile.getInputStream();
			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

		} catch (JsonProcessingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (filecontent != null) {
				try {
					filecontent.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}
		return readExcelFile(uploadFilePath);
	}

	public String readExcelFile(String uploadFilePath) {
		InputStream fileToRead = null;
		XSSFWorkbook workbook = null;
		HSSFWorkbook hssfworkbook = null;
		XSSFRow row;
		XSSFCell cell;
		Map<String, String> excelFileValue = null;
		List<Map<String, String>> excelTotalValue = null;
		List<String> addfilename = null;
		List<String> duplicateFileName = null;
		List<String> cellHeaderobj = null;
		ObjectMapper objmap = null;
		String jsonmap = null;
		try {
			fileToRead = new FileInputStream(uploadFilePath);
			String uploadFile = uploadFilePath.substring(uploadFilePath.lastIndexOf('.'));
			duplicateFileName = new ArrayList<>();
			addfilename = new ArrayList<>();
			excelTotalValue = new ArrayList<>();
			cellHeaderobj = new LinkedList<>();
			objmap = new ObjectMapper();

			if (uploadFile.equalsIgnoreCase(".xlsx")) {

				workbook = new XSSFWorkbook(fileToRead);
				XSSFSheet sheet = workbook.getSheetAt(0);
				int cellnumber = sheet.getRow(0).getLastCellNum();
				int rownumber = sheet.getLastRowNum();

				for (int i = 0; i < cellnumber; i++) {
					cellHeaderobj.add(sheet.getRow(0).getCell(i).toString());
				}
				for (int j = 1; j <= rownumber; j++) {
					excelFileValue = new HashMap<>();

					for (int i = 0; i < cellnumber; i++) {
						excelFileValue.put(sheet.getRow(0).getCell(i).toString(),
								sheet.getRow(j).getCell(i).toString());

						if (((sheet.getRow(0).getCell(i)).toString().equalsIgnoreCase("filename")) && (addfilename
								.toString().contains(sheet.getRow(j).getCell(i).toString().toLowerCase()))) {

							for (String addfile : addfilename) {
								if (addfile.equalsIgnoreCase(sheet.getRow(j).getCell(i).toString())) {
									duplicateFileName.add(sheet.getRow(j).getCell(i).toString());
								}
							}
						}

						else if ((sheet.getRow(0).getCell(i)).toString().equalsIgnoreCase("filename")) {

							if (sheet.getRow(j).getCell(i).getCellType() == XSSFCell.CELL_TYPE_STRING) {
								addfilename.add(sheet.getRow(j).getCell(i).getStringCellValue().toLowerCase());
							} else {
								addfilename.add(sheet.getRow(j).getCell(i).getRawValue());
							}

						}
					}
					excelTotalValue.add(excelFileValue);
				}
			}

			else if (uploadFile.equalsIgnoreCase(".xls")) {

				hssfworkbook = new HSSFWorkbook(fileToRead);
				HSSFSheet sheet = hssfworkbook.getSheetAt(0);
				Row hssfrow = sheet.getRow(0);
				int totalCol = hssfrow.getLastCellNum();
				int totalRow = sheet.getLastRowNum();
				for (int coll = 0; coll < totalCol; coll++) {
					cellHeaderobj.add(sheet.getRow(0).getCell(coll).toString());
				}
				for (int roww = 1; roww <= totalRow; roww++) {
					excelFileValue = new HashMap<>();

					for (int coll = 0; coll < totalCol; coll++) {
						excelFileValue.put(sheet.getRow(0).getCell(coll).toString(),
								sheet.getRow(roww).getCell(coll).toString());

						if (((sheet.getRow(0).getCell(coll)).toString().equalsIgnoreCase("filename")) && (addfilename
								.toString().contains(sheet.getRow(roww).getCell(coll).toString().toLowerCase()))) {

							for (String addfile : addfilename) {
								if (addfile.equalsIgnoreCase(sheet.getRow(roww).getCell(coll).toString())) {
									duplicateFileName.add(sheet.getRow(roww).getCell(coll).toString());
								}
							}
						}

						else if ((sheet.getRow(0).getCell(coll)).toString().equalsIgnoreCase("filename")) {
							if (sheet.getRow(roww).getCell(coll).getCellType() == HSSFCell.CELL_TYPE_STRING) {
								addfilename.add(sheet.getRow(roww).getCell(coll).getStringCellValue().toLowerCase());
							} else {
								addfilename.add(sheet.getRow(roww).getCell(coll).toString());
							}

						}
					}
					excelTotalValue.add(excelFileValue);
				}
			}

			else {
				System.out.println("Upload Excel File");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileToRead != null) {
				try {
					fileToRead.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		if (!duplicateFileName.isEmpty()) {
			try {
				jsonmap = objmap.writerWithDefaultPrettyPrinter()
						.writeValueAsString("<b>Duplicate Filename in Excel:</b>  " + duplicateFileName);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return jsonmap;

		} else {
			return filterRepository.checkDBFileName(addfilename, excelTotalValue, cellHeaderobj);
		}

	}

	@Override
	public String updateValuePojo(List<Map<String, String>> excelTotalValue, List<String> cellHeaderobj) {
		List<AudioTable> audioTableObj = null;
		try {
			audioTableObj = new ArrayList<>();
			for (int j = 0; j < excelTotalValue.size(); j++) {
				AudioTable audioTable = new AudioTable();
				String projectName = null;
				String audioType = null;
				String str = null;
				String audioPath = null;
				ObjectMapper mapper = new ObjectMapper();
				String jsonReturn = null;
				for (int i = 0; i < cellHeaderobj.size(); i++) {

					if (cellHeaderobj.get(i).equalsIgnoreCase("audioinput")) {
						audioTable.setAudioInput(excelTotalValue.get(j).get(cellHeaderobj.get(i)));
					}
					if (cellHeaderobj.get(i).equalsIgnoreCase("gender")) {
						audioTable.setGender(excelTotalValue.get(j).get(cellHeaderobj.get(i)));
					}
					if (cellHeaderobj.get(i).equalsIgnoreCase("language")) {
						audioTable.setLanguage(excelTotalValue.get(j).get(cellHeaderobj.get(i)));
					}
					if (cellHeaderobj.get(i).equalsIgnoreCase("voicename")) {
						audioTable.setVoiceName(excelTotalValue.get(j).get(cellHeaderobj.get(i)));
					}
					if (cellHeaderobj.get(i).equalsIgnoreCase("audiotype")) {
						audioTable.setAudioType(excelTotalValue.get(j).get(cellHeaderobj.get(i)));
						audioType = excelTotalValue.get(j).get(cellHeaderobj.get(i));
					}
					if (cellHeaderobj.get(i).equalsIgnoreCase("filename")) {
						audioTable.setFileName(excelTotalValue.get(j).get(cellHeaderobj.get(i)));
					}
					if (cellHeaderobj.get(i).equalsIgnoreCase("audiofilepath")) {
						audioPath = excelTotalValue.get(j).get(cellHeaderobj.get(i));
						audioTable.setAudioFilePath(audioPath);
					}
					if (cellHeaderobj.get(i).equalsIgnoreCase("projectName")) {
						audioTable.setProjectName(excelTotalValue.get(j).get(cellHeaderobj.get(i)));
						projectName = excelTotalValue.get(j).get(cellHeaderobj.get(i));
					}
					if ((audioType != null) && (projectName != null)) {
						List<Object> extensionIdList = new ArrayList();
						str = filterRepository.getAudioPath(audioType, projectName);
						audioTable.setAudioStatus("InProgress");
						Map<String, Object> jsonMap = mapper.readValue(str, new TypeReference<Map<String, Object>>() {
						});

						extensionIdList = (List<Object>) jsonMap.get("audioFilePath");
						int listSize = extensionIdList.size();
						if (jsonMap.get("ProjectAllocationID") != null && jsonMap.get("ExcelCheckAudioPath")!=null) {
							ProjectAllocation projectAllocate = new ProjectAllocation();

							projectAllocate.setAllocationId(Long.valueOf((String) jsonMap.get("ProjectAllocationID")));
							audioTable.setProjectAllocation(projectAllocate);
							
							for (int k = 0; k < listSize; k++) {
								Map<String, Object> extensionMap = new HashMap<>();
								extensionMap = (Map<String, Object>) extensionIdList.get(k);
								
								if (audioPath.equals((String)extensionMap.get("audioPaths"))) {
									
									if ((extensionMap.get("ExtensionID"))!= null) {
									
										AFPathConfig afPathConfig = new AFPathConfig();
										afPathConfig.setId(Long.valueOf((String)extensionMap.get("ExtensionID")));
										audioTable.setaFPathConfig(afPathConfig);
               
									}
									

								}
								
						
							}
							
						}
					
							else {
						
							jsonReturn = mapper
									.writeValueAsString("Mismatch Configuration in Project Path for Particular user");
							return jsonReturn;
						}
					}
				}
				audioTableObj.add(audioTable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filterRepository.updateExcelInDB(audioTableObj);
	}

	@Override
	public List<AudioTable> findExcelFile(Part excelFilePath) {

		InputStream fileToRead = null;
		XSSFWorkbook workbook = null;
		HSSFWorkbook hssfworkbook = null;
		XSSFRow row;
		XSSFCell cell;
		List<AudioTable> addfilename = null;
		String excelFile = null;
		File excelFileRead = null;
		ObjectMapper objMap = new ObjectMapper();
		String jsonMap = null;
		try {
			String UPLOADED_FOLDER = bundle.getString("ExcelFilePath");
			excelFile = UPLOADED_FOLDER + "/" + excelFilePath.getSubmittedFileName();
			excelFileRead = new File(excelFile);
			fileToRead = new FileInputStream(excelFileRead);
			String uploadFile = excelFile.substring(excelFile.lastIndexOf('.'));
			addfilename = new ArrayList<>();

			if (uploadFile.equalsIgnoreCase(".xlsx")) {

				workbook = new XSSFWorkbook(fileToRead);
				XSSFSheet sheet = workbook.getSheetAt(0);
				int cellnumber = sheet.getRow(0).getLastCellNum();
				int rownumber = sheet.getLastRowNum();
				for (int j = 1; j <= rownumber; j++) {
					AudioTable audioTable = new AudioTable();
					for (int i = 0; i < cellnumber; i++) {
						if (((sheet.getRow(0).getCell(i)).toString().equalsIgnoreCase("audiotype"))) {

							audioTable.setAudioType(sheet.getRow(j).getCell(i).getStringCellValue().toLowerCase());
						} else if (((sheet.getRow(0).getCell(i)).toString().equalsIgnoreCase("filename"))) {

							if (sheet.getRow(j).getCell(i).getCellType() == XSSFCell.CELL_TYPE_STRING) {
								audioTable.setFileName(sheet.getRow(j).getCell(i).getStringCellValue().toLowerCase());
							} else {
								audioTable.setFileName(sheet.getRow(j).getCell(i).getRawValue());
							}

						} else if (((sheet.getRow(0).getCell(i)).toString().equalsIgnoreCase("audiofilepath"))) {
							audioTable.setAudioFilePath(sheet.getRow(j).getCell(i).getStringCellValue());

						} else if (((sheet.getRow(0).getCell(i)).toString().equalsIgnoreCase("audioInput"))) {
							audioTable.setAudioInput(sheet.getRow(j).getCell(i).getStringCellValue());

						} else if (((sheet.getRow(0).getCell(i)).toString().equalsIgnoreCase("gender"))) {
							audioTable.setGender(sheet.getRow(j).getCell(i).getStringCellValue());

						} else if (((sheet.getRow(0).getCell(i)).toString().equalsIgnoreCase("language"))) {
							audioTable.setLanguage(sheet.getRow(j).getCell(i).getStringCellValue());

						} else if (((sheet.getRow(0).getCell(i)).toString().equalsIgnoreCase("voiceName"))) {
							audioTable.setVoiceName(sheet.getRow(j).getCell(i).getStringCellValue());

						}

					}
					addfilename.add(audioTable);
				}
			}

			else if (uploadFile.equalsIgnoreCase(".xls")) {

				hssfworkbook = new HSSFWorkbook(fileToRead);
				HSSFSheet sheet = hssfworkbook.getSheetAt(0);
				Row hssfrow = sheet.getRow(0);
				int totalCol = hssfrow.getLastCellNum();
				int totalRow = sheet.getLastRowNum();
				for (int roww = 1; roww <= totalRow; roww++) {
					AudioTable audioTable = new AudioTable();
					for (int coll = 0; coll < totalCol; coll++) {
						if ((sheet.getRow(0).getCell(coll)).toString().equalsIgnoreCase("audiotype")) {
							audioTable
									.setAudioType(sheet.getRow(roww).getCell(coll).getStringCellValue().toLowerCase());

						} else if ((sheet.getRow(0).getCell(coll)).toString().equalsIgnoreCase("filename")) {
							if (sheet.getRow(roww).getCell(coll).getCellType() == HSSFCell.CELL_TYPE_STRING) {
								audioTable.setFileName(
										sheet.getRow(roww).getCell(coll).getStringCellValue().toLowerCase());
							} else {
								audioTable.setFileName(sheet.getRow(roww).getCell(coll).toString());
							}

						} else if ((sheet.getRow(0).getCell(coll)).toString().equalsIgnoreCase("audiofilepath")) {
							audioTable.setAudioFilePath(sheet.getRow(roww).getCell(coll).getStringCellValue());

						}
					}
					addfilename.add(audioTable);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileToRead != null) {
				try {
					fileToRead.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (excelFileRead != null) {
				excelFileRead.delete();
			}
		}
		try {
			jsonMap = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(addfilename);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return addfilename;
	}

	/*
	 * @Override public String checkAudioFile(List<AudioTable> addfilename) {
	 * int count = 0; ObjectMapper objMap = new ObjectMapper(); String jsonMap =
	 * null; Map<String, Object> audioFileMap = null; List<Map<String, Object>>
	 * audioFileList = null; List<String> notCreatedFileList = null; List
	 * fileList = null;
	 * 
	 * try { audioFileList = new ArrayList<>(); notCreatedFileList = new
	 * ArrayList<>(); fileList = new ArrayList<>(); for (int i = 0; i <
	 * addfilename.size(); i++) { String filePath =
	 * bundle.getString("CreatedAudioFilePath"); File folder = new File(filePath
	 * + '/' + addfilename.get(i).getAudioFilePath() + '/' +
	 * addfilename.get(i).getFileName() + "." +
	 * addfilename.get(i).getAudioType()); audioFileMap = new HashMap<>(); if
	 * (folder.isFile()) { count++; audioFileMap.put("fileNameSize",
	 * addfilename.size()); audioFileMap.put("audioCreatedCount", count);
	 * audioFileMap.put("fileName", folder.getName()); } else {
	 * System.out.println("File not Found"); audioFileMap.put("fileNameSize",
	 * addfilename.size()); audioFileMap.put("audioCreatedCount", count);
	 * notCreatedFileList.add(folder.getName()); }
	 * audioFileList.add(audioFileMap); } fileList.add(notCreatedFileList);
	 * fileList.add(audioFileList); jsonMap =
	 * objMap.writerWithDefaultPrettyPrinter().writeValueAsString(fileList); }
	 * catch (Exception e) { e.printStackTrace(); } return jsonMap; }
	 */

	@Override
	public int checkSingleAudioFile(AudioTable audioTable) {
		List<AudioTable> reqBody = new ArrayList<>();
		reqBody.add(audioTable);
		AudioTable temp = new AudioTable();
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<WebserviceResponseBean[]> response = restTemplate
					.postForEntity(bundle.getString("APISinglAudiourl"), reqBody, WebserviceResponseBean[].class);
			if (response.getBody()[0].getFileStatus() == 1) {
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				temp.setFileName(audioTable.getFileName());
				temp.setCreatedDateTime(formatter.parse(response.getBody()[0].getCreatedTime()));
				temp.setLastModifiedDateTime(formatter.parse(response.getBody()[0].getModifiedTime()));
				temp.setAudioStatus("Success");
				filterRepository.updateAudioTable(temp);
			} else {
				temp.setAudioStatus("Failure");
				temp.setFileName(audioTable.getFileName());
				filterRepository.updateAudioTable(temp);
			}
			return response.getBody()[0].getFileStatus();
		} catch (Exception e) {
			temp.setAudioStatus("Failure");
			temp.setFileName(audioTable.getFileName());
			filterRepository.updateAudioTable(temp);
			return 2;
		}

	}

	public List<WebserviceResponseBean> sendMultiAudio(List<AudioTable> audioTable) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WebserviceResponseBean[]> response = null;
		List<WebserviceResponseBean> result = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		AudioTable temp = new AudioTable();
		HttpEntity<List<AudioTable>> request = new HttpEntity<List<AudioTable>>(audioTable, headers);
		try {
			response = restTemplate.postForEntity(bundle.getString("APIMultiAudiourl"), request,
					WebserviceResponseBean[].class);
			for (int i = 0; i < response.getBody().length; i++) {
				if (response.getBody()[i].getFileStatus() == 0) {
					WebserviceResponseBean wsTemp = response.getBody()[i];
					wsTemp.setProjectName(audioTable.get(i).getProjectName());
					wsTemp.setLanguage(audioTable.get(i).getLanguage());
					wsTemp.setAudioType(audioTable.get(i).getAudioType());
					wsTemp.setVoiceName(audioTable.get(i).getVoiceName());
					wsTemp.setAudioFilePath(audioTable.get(i).getAudioFilePath());
					wsTemp.setGender(audioTable.get(i).getGender());
					wsTemp.setAudioInput(audioTable.get(i).getAudioInput());
					wsTemp.setExtensionPathId(audioTable.get(i).getaFPathConfig());
					wsTemp.setProjectAllocationId(audioTable.get(i).getProjectAllocation());
					wsTemp.setFileName(audioTable.get(i).getFileName());
					temp.setFileName(response.getBody()[i].getFileName());
					temp.setAudioStatus("Failure");
					filterRepository.updateAudioTable(temp);
					result.add(wsTemp);
				} else {
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					result.add(response.getBody()[i]);
					temp.setFileName(audioTable.get(i).getFileName());
					temp.setAudioStatus("Success");
					try {
						temp.setCreatedDateTime(formatter.parse(response.getBody()[i].getCreatedTime()));
						temp.setLastModifiedDateTime(formatter.parse(response.getBody()[i].getModifiedTime()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					filterRepository.updateAudioTable(temp);
				}

			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(audioTable.size());
			for (int i = 0; i < audioTable.size(); i++) {
				WebserviceResponseBean wsTemp = new WebserviceResponseBean();
				wsTemp.setReturnCode(0);
				wsTemp.setErrorMessage("Unable to Connect WS");
				wsTemp.setFileName(audioTable.get(i).getFileName());
				wsTemp.setProjectName(audioTable.get(i).getProjectName());
				wsTemp.setLanguage(audioTable.get(i).getLanguage());
				wsTemp.setAudioType(audioTable.get(i).getAudioType());
				wsTemp.setVoiceName(audioTable.get(i).getVoiceName());
				wsTemp.setAudioFilePath(audioTable.get(i).getAudioFilePath());
				wsTemp.setGender(audioTable.get(i).getGender());
				wsTemp.setAudioInput(audioTable.get(i).getAudioInput());
				wsTemp.setExtensionPathId(audioTable.get(i).getaFPathConfig());
				wsTemp.setProjectAllocationId(audioTable.get(i).getProjectAllocation());
				temp.setFileName(audioTable.get(i).getFileName());
				temp.setAudioStatus("failure");
				System.out.println(temp);
				filterRepository.updateAudioTable(temp);
				result.add(wsTemp);
			}
		}
		return result;
	}

}
