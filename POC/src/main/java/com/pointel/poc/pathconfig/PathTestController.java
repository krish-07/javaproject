package com.pointel.poc.pathconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pointel.poc.db.entity.PathCheckBean;

@RestController
public class PathTestController {
	@Autowired
	private PathConfigService filePathConfigService;
	
	@GetMapping("pathConfig")
	public ModelAndView pathConfig() {
		return new ModelAndView("pathConfig");
	}
	
	@PostMapping(value = "LoadPathPropertiesValue", produces = MediaType.APPLICATION_JSON_VALUE)
	public String loadPathPropertiesValue() {
		return filePathConfigService.loadPathValues();
	}
	
	@PostMapping(value = "checkPath", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean checkPath(@ModelAttribute PathCheckBean pathCheck) {
		return filePathConfigService.checkPath(pathCheck);
	}
	
	@PostMapping(value = "CheckAudioPath", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean checkAudioPath(@ModelAttribute PathCheckBean checkAudioPath) {
		return filePathConfigService.checkAudioPath(checkAudioPath);
	}
	
	@PostMapping(value = "changeConfigPath", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean changeConfigPath(@ModelAttribute PathCheckBean pathCheck) {
		return filePathConfigService.changeConfigPath(pathCheck);
	}
	
	/*@PostMapping(value = "saveFilePathDB", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean saveFilePathDB(@ModelAttribute PathCheckBean pathCheck) {
		return filePathConfigService.saveFilePathDB(pathCheck);
	}*/
	
}
