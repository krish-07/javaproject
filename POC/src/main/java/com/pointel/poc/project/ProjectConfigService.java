package com.pointel.poc.project;

import com.pointel.poc.db.entity.AFPathConfig;
import com.pointel.poc.db.entity.Project;

public interface ProjectConfigService {

	String getAllProject();

	String addNewProject(Project project);

	String updateProjectInfo(Project project);

	String getFolderByProject(String projectId);

	boolean removeProject(String projectId);

	boolean addFolderByProject(AFPathConfig afPathConfig);

	boolean deletetFolderByProject(String projectId, String folderList);
}
