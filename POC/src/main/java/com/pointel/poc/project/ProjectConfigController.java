package com.pointel.poc.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pointel.poc.db.entity.AFPathConfig;
import com.pointel.poc.db.entity.Project;

@RestController
public class ProjectConfigController {
	@Autowired
	private ProjectConfigService projectConfigService;

	@GetMapping("ProjectConfig")
	public ModelAndView home() {
		return new ModelAndView("ProjectConfig");
	}

	@PostMapping(value = "GetAllProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllProjects() {
		return projectConfigService.getAllProject();
	}

	@PostMapping(value = "AddNewProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addNewProject(@ModelAttribute Project project) {
		System.out.println(project);
		return projectConfigService.addNewProject(project);
	}

	@PostMapping(value = "UpdateProjectInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateProjectInfo(@ModelAttribute Project project) {
		System.out.println(project);
		
		return projectConfigService.updateProjectInfo(project);
	}

	@PostMapping(value = "RemoveProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean removeProject(@RequestParam("projectId") String projectId) {
		return projectConfigService.removeProject(projectId);
	}

	@PostMapping(value = "AddFolderByProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addFolderByProject(@ModelAttribute("projectId") AFPathConfig afPathConfig) {
		return projectConfigService.addFolderByProject(afPathConfig);
	}
	
	
	
	@PostMapping(value = "DeleteFolderFromProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deletetFolder(@RequestParam("projectId") String projectId, @RequestParam("folderList") String folderList) {
		return projectConfigService.deletetFolderByProject(projectId,folderList);
	}
	
	

	@PostMapping(value = "GetFolderByProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getFolderByProject(@RequestParam("projectId") String projectId) {
		System.out.println(projectId);
		return projectConfigService.getFolderByProject(projectId);
	}

}
