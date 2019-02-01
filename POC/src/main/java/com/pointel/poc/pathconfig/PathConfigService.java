package com.pointel.poc.pathconfig;

import com.pointel.poc.db.entity.PathCheckBean;

public interface PathConfigService {

	String loadPathValues();

	boolean checkPath(PathCheckBean pathCheck);
	
	boolean changeConfigPath(PathCheckBean pathCheck);

	boolean checkAudioPath(PathCheckBean checkAudioPath);


}
