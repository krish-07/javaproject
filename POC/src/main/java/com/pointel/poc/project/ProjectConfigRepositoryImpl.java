package com.pointel.poc.project;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointel.poc.db.entity.AFPathConfig;
import com.pointel.poc.db.entity.AudioExtension;
import com.pointel.poc.db.entity.Project;

@Transactional
@Repository
public class ProjectConfigRepositoryImpl implements ProjectConfigRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Project> getAllProject() {

		List<Project> projectList = null;
		try {
			CriteriaQuery<Project> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Project.class);
			criteriaQuery.select(criteriaQuery.from(Project.class));
			projectList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return projectList;
	}

	@Override
	public String addNewProject(Project project) {
		String isAdded = "404";
		try {
			if (checkProjectName(project)) {
				entityManager.persist(project);
				isAdded = "200";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAdded;

	}

	private boolean checkProjectName(Project project) {
		List<Project> projectList = getAllProject();

		for (Project existingProject : projectList) {
			if (existingProject.getProjectName().equalsIgnoreCase(project.getProjectName())) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String updateProjectInfo(Project project) {
		String isUpdated = "404";
		try {
			if (checkEditProjectName(project)) {
				entityManager.merge(project);
				isUpdated = "200";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isUpdated;

	}

	private boolean checkEditProjectName(Project project) {
		List<Project> projectList = getAllProject();
		for (Project existingProject : projectList) {
			if (existingProject.getProjectName().equalsIgnoreCase(project.getProjectName())
					&& existingProject.getProjectId() == project.getProjectId()) {
				return true;
			}

			if (existingProject.getProjectName().equalsIgnoreCase(project.getProjectName())
					&& existingProject.getProjectId() != project.getProjectId()) {
				return false;
			}

		}
		return true;

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getFolderByProject(String projectId) {
		String jsonPath = null;
		List<AFPathConfig> pathList = null;
		try {
			Query query = entityManager.createQuery("from AFPathConfig  WHERE  Project_ID = :PROJECTID");
			query.setParameter("PROJECTID", Long.parseLong(projectId));
			pathList = query.getResultList();
			jsonPath = new ObjectMapper().writeValueAsString(pathList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonPath;
	}

	@Override
	public boolean removeProject(String projectId) {
		boolean isRemoved = false;
		try {
			entityManager.remove(entityManager.getReference(Project.class, Long.parseLong(projectId)));
			isRemoved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isRemoved;
	}

	@Override
	public boolean addFolderByProject(AFPathConfig afPathConfig) {
		boolean isAdded = false;
		try {

			AudioExtension audioExtension = getExtensionType(afPathConfig.getExtension());
			afPathConfig.setAudioExtension(audioExtension);
			afPathConfig
					.setProject(entityManager.getReference(Project.class, Long.parseLong(afPathConfig.getProjectId())));
			entityManager.persist(afPathConfig);
			isAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdded;
	}

	private AudioExtension getExtensionType(String extension) {
		AudioExtension audioExtension = null;
		try {
			Query query = entityManager.createQuery("from AudioExtension  WHERE  ExtensionType = :EXTENSION");
			query.setParameter("EXTENSION", extension);
			audioExtension = (AudioExtension) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return audioExtension;
	}

	@Override
	public boolean deletetFolderByProject(String projectId, String folderList) {
		boolean isDeleted = false;
		try {
			String queryString = "Delete from AFPathConfig  WHERE  Project_ID = :PROJECTID AND ExtensionPath_ID IN("
					+ folderList + ")";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("PROJECTID", projectId);

			System.out.println(query.executeUpdate());
			isDeleted = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

}
