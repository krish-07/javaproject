package com.pointel.poc.add;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pointel.poc.db.entity.AFPathConfig;
import com.pointel.poc.db.entity.AudioTable;
import com.pointel.poc.db.entity.ProjectAllocation;
import com.pointel.poc.db.entity.WebserviceResponseBean;

@RestController
public class AddFilterController {
	@Autowired
	private AddFilterService filterService;

	@GetMapping("Add")
	public ModelAndView add() {
		return new ModelAndView("Add");
	}

	@PostMapping(value = "CheckFileName", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean checkFileName(@RequestParam("fileName") String fileName) {
		return filterService.checkFileName(fileName);
	}

	
	@PostMapping(value = "GetProjectName", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getProjectName(HttpSession session) {
		
		if(session.getAttribute("POCUSERID")!=null){
			long userId=(long) session.getAttribute("POCUSERID");
		return filterService.getProjectName(userId);
		}
		return null;
	}

	@PostMapping(value = "GetAudioType", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAudioType(@RequestParam("projectNameGet") String projectName) {
		return filterService.getAudioType(projectName);
	}

	@PostMapping(value = "GetVoiceName", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getVoiceName(@RequestParam("langGet") String language) {
		
		return filterService.getVoiceName(language);
	}

	@PostMapping(value = "GetAudioPath", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAudioPath(@RequestParam("audioExtension") String audioExtension,
			@RequestParam("projectName") String projectName,HttpSession session) {
		
			return filterService.getAudioPath(audioExtension, projectName);
		
	}

	@PostMapping(value = "addFilter", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addfilter(@ModelAttribute AudioTable audioTable,
			@RequestParam("extensionPathId") long extensionPathId,
			@RequestParam("projectAllocationId") long projectAllocationId) {
		
		ProjectAllocation projectAllot = new ProjectAllocation();
		projectAllot.setAllocationId(projectAllocationId);
		audioTable.setProjectAllocation(projectAllot);
		AFPathConfig audioext = new AFPathConfig();
		audioext.setId(extensionPathId);
		audioTable.setaFPathConfig(audioext);
		audioTable.setAudioStatus("InProgress");
		return filterService.getaddFilteredResult(audioTable);

	}

	@PostMapping(value = "checkExcelFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkExcelFile(HttpServletRequest request) {
		Part uploadExcelFile = null;
		try {
			uploadExcelFile = request.getPart("file");

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		return filterService.uploadExcelFile(uploadExcelFile);	
	}
	
	
	@PostMapping(value = "readUploadExcelFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WebserviceResponseBean> readUploadExcelFile(HttpServletRequest request) {
		Part excelFilePath = null;
		try {
			excelFilePath = request.getPart("file");

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		List<AudioTable> res=filterService.findExcelFile(excelFilePath);
		return filterService.sendMultiAudio(res);
	}
	/*@PostMapping(value = "checkAudioFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkAudioFile(@RequestBody List<AudioTable> excelData) {
		return filterService.checkAudioFile(excelData);
	}*/

	@PostMapping(value = "checkSingleAudio", produces = MediaType.APPLICATION_JSON_VALUE)
	public int checkSingleAudio(@ModelAttribute AudioTable audioTable) {
		return filterService.checkSingleAudioFile(audioTable);
	}
	
	
	
}
