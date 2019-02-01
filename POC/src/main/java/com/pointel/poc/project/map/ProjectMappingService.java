package com.pointel.poc.project.map;

public interface ProjectMappingService {

	String getProjectByUserId(long userId);

	boolean mapProject(long userId, String projectId, String serviceId, String mode);
}
