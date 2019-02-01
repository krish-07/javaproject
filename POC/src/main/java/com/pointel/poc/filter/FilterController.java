package com.pointel.poc.filter;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pointel.poc.db.entity.AudioTable;

@RestController
public class FilterController {
	@Autowired
	private FilterService filterService;

	@GetMapping("Trace")
	public ModelAndView home() {
		return new ModelAndView("Trace");
	}

	@PostMapping(value = "OnloadProp", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onloadTrace(HttpSession session) {
		return filterService.onLoadProp(session.getAttribute("POCUSERID").toString());
	}

	@PostMapping(value = "Filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public String filter(@ModelAttribute AudioTable audioTable, HttpSession session) {
		return filterService.getFilteredResult(audioTable, session.getAttribute("POCUSERID").toString());
	}
	
	@PostMapping(value = "getGender", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getGender(@ModelAttribute AudioTable audioTable, HttpSession session) {
		return filterService.getFilteredResult(audioTable, session.getAttribute("POCUSERID").toString());
	}
	

	@PostMapping(value = "updateFilter", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateFilter(@ModelAttribute AudioTable audioTable,@RequestParam("extensionPathId") String extensionPathId) {
		return filterService.getUpdateFilteredResult(audioTable,extensionPathId);
	}

	@PostMapping("/UpdateAudio")
	public boolean updateAudio(@ModelAttribute AudioTable audioTable) {
		return filterService.updateAudio(audioTable);
	}
	@PostMapping(value = "DeleteAudioRecord", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteAudioRecord(@RequestParam("AudioId") String audioId) {
		return filterService.deleteAudioRecord(audioId);
	}

	@PostMapping(value = "FileExist", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean fileExist(@RequestParam("FileName") String fileName) {
		return filterService.checkFileExist(fileName);
	}
	@PostMapping(value = "AudioFilePath", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAudioFilePath(@RequestParam("projectName") String projectName,@RequestParam("audioType") String audioType,HttpSession session) {
		return filterService.getAudioFilePathService(session.getAttribute("POCUSERID").toString(),projectName,audioType);
	}

	
	@ModelAttribute
	@GetMapping("/PlayAudio")
	public void playAudio(@RequestParam("fileName") String fileName, HttpServletResponse response,
			HttpServletRequest request, Model model) {
		try {
			model.addAttribute("AudioAvail", "false");
			byte[] audioBytes = filterService.readAudio(fileName);
			if (audioBytes != null) {
				response.setContentType("audio/*");
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "Audio");
				ServletOutputStream outStream;
				outStream = response.getOutputStream();
				outStream.write(audioBytes);
				outStream.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
