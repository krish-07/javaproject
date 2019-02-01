package com.pointel.poc.project.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointel.poc.db.entity.Login;
import com.pointel.poc.db.entity.PocMenu;
import com.pointel.poc.db.entity.Project;
import com.pointel.poc.db.entity.ProjectAllocation;

@Transactional
@Repository
public class ProjectMappingRepositoryImpl implements ProjectMappingRepository {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public String getProjectByUserId(long userId) {
		String mappingJson = null;
		try {
			Map<String, Object> mainMap = new HashMap<>();
			CriteriaQuery<Project> projectCriteriaQuery = entityManager.getCriteriaBuilder().createQuery(Project.class);
			projectCriteriaQuery.select(projectCriteriaQuery.from(Project.class));

			CriteriaQuery<PocMenu> serviceCriteriaQuery = entityManager.getCriteriaBuilder().createQuery(PocMenu.class);
			serviceCriteriaQuery.select(serviceCriteriaQuery.from(PocMenu.class));

			mainMap.put("project", entityManager.createQuery(projectCriteriaQuery).getResultList());
			mainMap.put("service", entityManager.createQuery(serviceCriteriaQuery).getResultList());

			List<Object> mappingDetails = new ArrayList<>();
			Query query = entityManager.createNativeQuery(MapProjectQueryConstant.GETPROJECTMAPPINGQUERY.getSqlQuery());
			query.setParameter("USERID", userId);
			List<Object[]> apiList = query.getResultList();
			for (Object[] project : apiList) {
				Map<String, Object> userProjectList = new HashMap<>();
				userProjectList.put("ProjectId", project[0].toString());
				userProjectList.put("ProjectName", project[1].toString());
				userProjectList.put("ServiceId", project[2].toString());
				userProjectList.put("ServiceName", project[3].toString());
				mappingDetails.add(userProjectList);
			}
			mainMap.put("mappedProject", mappingDetails);
			mappingJson = new ObjectMapper().writeValueAsString(mainMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mappingJson;
	}

	@Override
	public boolean mapProject(long userId, String projectIds, String serviceId) {
		boolean isAllocated = false;
		try {
			if (!projectIds.contains(",")) {
				ProjectAllocation updateprojectAllocation = new ProjectAllocation();
				updateprojectAllocation
						.setProject(entityManager.getReference(Project.class, Long.parseLong(projectIds)));
				updateprojectAllocation.setLogin(entityManager.getReference(Login.class, userId));
				updateprojectAllocation
						.setPocMenu(entityManager.getReference(PocMenu.class, Long.parseLong(serviceId)));
				entityManager.persist(updateprojectAllocation);
				isAllocated = true;
			} else {
				String[] projectIdArray = projectIds.split(",");
				for (String projectId : projectIdArray) {
					ProjectAllocation updateprojectAllocation = new ProjectAllocation();
					updateprojectAllocation
							.setProject(entityManager.getReference(Project.class, Long.parseLong(projectId)));
					updateprojectAllocation.setLogin(entityManager.getReference(Login.class, userId));
					updateprojectAllocation
							.setPocMenu(entityManager.getReference(PocMenu.class, Long.parseLong(serviceId)));
					entityManager.persist(updateprojectAllocation);
					isAllocated = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAllocated;
	}

	@Override
	public boolean deleteProject(long userId, String projectIds, String serviceId) {
		boolean isDeleted = false;
		try {
			if (!projectIds.contains(",")) {
				Query query = entityManager.createQuery(
						"Delete from ProjectAllocation where user_Id = :USERID AND project_Id= :PROJECTID AND service_Id = :SERVICEID");
				query.setParameter("USERID", userId);
				query.setParameter("PROJECTID", projectIds);
				query.setParameter("SERVICEID", serviceId);
				isDeleted = 0 < query.executeUpdate();
			} else {
				String[] projectIdArray = projectIds.split(",");
				for (String projectId : projectIdArray) {
					Query query = entityManager.createQuery(
							"Delete from ProjectAllocation where user_Id = :USERID AND project_Id= :PROJECTID AND service_Id = :SERVICEID");
					query.setParameter("USERID", userId);
					query.setParameter("PROJECTID", projectId);
					query.setParameter("SERVICEID", serviceId);
					isDeleted = 0 < query.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDeleted;
	}
}
