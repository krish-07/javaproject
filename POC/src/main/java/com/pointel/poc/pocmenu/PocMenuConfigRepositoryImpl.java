package com.pointel.poc.pocmenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointel.poc.db.entity.PocMenu;

@Transactional
@Repository
public class PocMenuConfigRepositoryImpl implements PocMenuConfigRepository{

	@Autowired
	private EntityManager entityManager;
	
	private boolean checkEditServiceName(PocMenu pocMenu) {
		List<PocMenu> menuList = getAllMenuList();
		for (PocMenu existingService : menuList) {
			if( (existingService.getApiServiceName().equalsIgnoreCase(pocMenu.getApiServiceName()))
					&&( existingService.getServiceId() == pocMenu.getServiceId())) {
				return true;
			}

			if (existingService.getApiServiceName().equalsIgnoreCase(pocMenu.getApiServiceName())
					&& existingService.getServiceId() != pocMenu.getServiceId()) {
				return false;
			}

		}
		return true;

	}
	
	@Override
	public String getAllMenu() {
		String menuListJson = null;
		try {
			CriteriaQuery<PocMenu> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(PocMenu.class);
			criteriaQuery.select(criteriaQuery.from(PocMenu.class));
			List<PocMenu> menuList = entityManager.createQuery(criteriaQuery).getResultList();
			Map<String, Object> menuMap = new HashMap<>();
			menuMap.put("data", menuList);
			menuListJson = new ObjectMapper().writeValueAsString(menuMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuListJson;
	}
	
	private List<PocMenu> getAllMenuList() {
		List<PocMenu> menuList = null;
		try {
			CriteriaQuery<PocMenu> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(PocMenu.class);
			criteriaQuery.select(criteriaQuery.from(PocMenu.class));
			menuList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}

	@Override
	public boolean editMenu(PocMenu pocMenu) {
		boolean isUpdated = false;
		try {

			if (checkEditServiceName(pocMenu)) {
				
				entityManager.merge(pocMenu);
				isUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isUpdated;
	}

	@Override
	public boolean deleteService(long serviceId) {
		boolean isDeleted = false;
		try {
			entityManager.remove(entityManager.getReference(PocMenu.class, serviceId));
			isDeleted = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDeleted;
	}
	
	@Override
	public boolean addService(PocMenu pocMenu) {
		boolean isAdded = false;
		try {

			if (checkServiceName(pocMenu)) {
				entityManager.persist(pocMenu);
				isAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAdded;
	}
	
	private boolean checkServiceName(PocMenu pocMenu) {
		List<PocMenu> menuList = getAllMenuList();
		for (PocMenu existingMenu : menuList) {
			if (existingMenu.getApiServiceName().equalsIgnoreCase(pocMenu.getApiServiceName()) && pocMenu.getServiceId() == 0) {
				return false;
			}

		}
		return true;
	}


}
