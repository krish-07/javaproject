package com.pointel.poc.dbconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pointel.poc.db.entity.DBConnectionBean;

@RestController
public class DBTestController {
	@Autowired
	private DatabaseConfigService dbConfigService;
	
	@GetMapping("dbConfig")
	public ModelAndView dbConfig() {
		return new ModelAndView("dbConfig");
	}
	
	
	@PostMapping(value = "LoadPropertiesValue", produces = MediaType.APPLICATION_JSON_VALUE)
	public String loadPropertiesValue() {
		return dbConfigService.loadDBValues();
	}
	
	
	@PostMapping(value = "DBConnection", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean dbConnection(@ModelAttribute DBConnectionBean dbConnection) {
		return dbConfigService.checkConnection(dbConnection);
	}
	
	@PostMapping(value = "changeConfigDB", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean changeConfigDB(@ModelAttribute DBConnectionBean dbConnection) {
		return dbConfigService.changeConfigDB(dbConnection);
	}
}
