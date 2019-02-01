package com.pointel.poc.project;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointel.poc.db.entity.AFPathConfig;
import com.pointel.poc.db.entity.Project;

@Service
public class ProjectConfigServiceImpl implements ProjectConfigService {

	@Autowired
	private ProjectConfigRepository userManagementRepository;

	@Override
	public String getAllProject() {
		String projectSJson = null;
		Map<String, Object> projectMap = null;
		try {
			projectMap = new HashMap<>();
			projectMap.put("project", userManagementRepository.getAllProject());
			projectSJson = new ObjectMapper().writeValueAsString(projectMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return projectSJson;
	}

	@Override
	public String addNewProject(Project project) {

		/*if (checkHostNameAndFolder(project)) {
			
		} else {
			return "405";
		}*/
		return userManagementRepository.addNewProject(project);
	}

	/*private boolean checkHostNameAndFolder(Project project) {
		boolean isValid = false;
		try {
			StringBuilder stringBuilder = new StringBuilder(
					"\\\\" + project.getHostName() + "\\" + project.getProjectPath());
			File file = new File(stringBuilder.toString());
			isValid = file.exists();

			System.out.println(stringBuilder.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isValid;
	}*/

	@Override
	public String updateProjectInfo(Project project) {
		/*if (checkHostNameAndFolder(project)) {
			return userManagementRepository.updateProjectInfo(project);
		} else {
			return "405";
		}*/
		return userManagementRepository.updateProjectInfo(project);
	}

	@Override
	public String getFolderByProject(String projectId) {
		return userManagementRepository.getFolderByProject(projectId);
	}

	@Override
	public boolean removeProject(String projectId) {
		return userManagementRepository.removeProject(projectId);
	}

	@Override
	public boolean addFolderByProject(AFPathConfig afPathConfig) {
		return userManagementRepository.addFolderByProject(afPathConfig);
	}

	@Override
	public boolean deletetFolderByProject(String projectId, String folderList) {
		return userManagementRepository.deletetFolderByProject(projectId,folderList);
	}

}
