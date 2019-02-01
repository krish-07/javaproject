package com.pointel.poc.user.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pointel.poc.db.entity.Login;

@RestController
public class UserManagementController {
	@Autowired
	private UserManagementService userManagementService;

	@GetMapping("UserManagement")
	public ModelAndView home() {
		return new ModelAndView("UserManagement");
	}

	@PostMapping(value = "GetAllUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllUser() {
		return userManagementService.getAllUser();
	}

	@PostMapping(value = "/AddNewUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addNewUser(@ModelAttribute Login login) {
		return userManagementService.addUser(login);
	}


	@PostMapping(value = "/EditUserinfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean editUserinfo(@ModelAttribute Login login) {
		return userManagementService.editUser(login);
	}
	@PostMapping(value = "/RemoveUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean editUserinfo(@RequestParam("userId") long userId) {
		return userManagementService.removeUser(userId);
	}
	
}
