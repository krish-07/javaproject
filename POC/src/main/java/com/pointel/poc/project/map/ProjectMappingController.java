package com.pointel.poc.project.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectMappingController {

	@Autowired
	private ProjectMappingService projectMappingService;

	@PostMapping(value = "/GetUserProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getProjectByUserId(@RequestParam("UserId") long userId) {
		return projectMappingService.getProjectByUserId(userId);
	}

	@PostMapping(value = "/MapProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean mapProject(@RequestParam("UserId") long userId, @RequestParam("ProjectId") String projectId,
			@RequestParam("ServiceId") String  serviceId,@RequestParam("Mode") String  mode) {
		return projectMappingService.mapProject(userId,projectId,serviceId,mode);
	}
}
