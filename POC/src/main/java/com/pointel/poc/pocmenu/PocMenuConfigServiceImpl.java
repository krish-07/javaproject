package com.pointel.poc.pocmenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointel.poc.db.entity.PocMenu;

@Service
public class PocMenuConfigServiceImpl implements PocMenuConfigService {

	@Autowired
	private PocMenuConfigRepository pocMenuConfigRepository;
	
	
	@Override
	public String getAllMenu() {
		return pocMenuConfigRepository.getAllMenu();
	}

	@Override
	public boolean addService(PocMenu pocMenu) {
		return pocMenuConfigRepository.addService(pocMenu);
	}

	@Override
	public boolean editMenu(PocMenu pocMenu) {
		return pocMenuConfigRepository.editMenu(pocMenu);
		
	}


	@Override
	public boolean deleteService(long serviceId) {
		return pocMenuConfigRepository.deleteService(serviceId);
	}

}
