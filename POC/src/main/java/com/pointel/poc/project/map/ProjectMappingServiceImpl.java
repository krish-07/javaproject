package com.pointel.poc.project.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectMappingServiceImpl implements ProjectMappingService {

	@Autowired
	private ProjectMappingRepository projectMappingRepository;

	@Override
	public String getProjectByUserId(long userId) {
		return projectMappingRepository.getProjectByUserId(userId);
	}

	@Override
	public boolean mapProject(long userId, String projectId, String serviceId, String mode) {

		if (mode.equalsIgnoreCase("ADD")) {
			return projectMappingRepository.mapProject(userId, projectId, serviceId);

		} else {
			return projectMappingRepository.deleteProject(userId, projectId, serviceId);
		}

	}
}
