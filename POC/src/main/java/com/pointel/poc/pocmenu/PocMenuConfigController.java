package com.pointel.poc.pocmenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pointel.poc.db.entity.PocMenu;

@RestController
public class PocMenuConfigController {
	
	@Autowired
	private PocMenuConfigService pocMenuConfigService;
	
	@GetMapping("pocMenu")
	public ModelAndView add() {
		return new ModelAndView("pocMenu");
	}

	@PostMapping(value = "GetAllMenu", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllUser() {
		return pocMenuConfigService.getAllMenu();
	}
	
	@PostMapping(value = "/AddNewService", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addNewService(@ModelAttribute PocMenu pocMenu) {
		return pocMenuConfigService.addService(pocMenu);
	}

	
	@PostMapping(value = "/EditMenuinfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean editMenuinfo(@ModelAttribute PocMenu pocMenu) {
		return pocMenuConfigService.editMenu(pocMenu);
	}
	
	@PostMapping(value = "/DeleteServiceRecord", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean editMenuinfo(@RequestParam("ServiceId") long serviceId) {
		return pocMenuConfigService.deleteService(serviceId);
	}
	
}
