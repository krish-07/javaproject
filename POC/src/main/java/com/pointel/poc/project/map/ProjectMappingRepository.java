package com.pointel.poc.project.map;

public interface ProjectMappingRepository {

	String getProjectByUserId(long userId);

	boolean mapProject(long userId, String projectId, String serviceId);

	boolean deleteProject(long userId, String projectIds, String serviceId);

}
