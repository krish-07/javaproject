package com.pointel.poc.pocmenu;

import com.pointel.poc.db.entity.PocMenu;

public interface PocMenuConfigService {

	String getAllMenu();

	boolean editMenu(PocMenu pocMenu);

	boolean deleteService(long serviceId);

	boolean addService(PocMenu pocMenu);

}
