package org.osforce.connect.service.map.impl;

import org.osforce.connect.dao.map.MapDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.entity.map.Map;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.service.map.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 17, 2011 - 3:51:11 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class MapServiceImpl implements MapService {

	private MapDao mapDao;
	private ProjectDao projectDao;
	
	public MapServiceImpl() {
	}
	
	@Autowired
	public void setMapDao(MapDao mapDao) {
		this.mapDao = mapDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	public Map getMap(Long mapId) {
		return mapDao.get(mapId);
	}

	public void createMap(Map map) {
		updateMap(map);
	}

	public void updateMap(Map map) {
		if(map.getProjectId()!=null) {
			Project project =  projectDao.get(map.getProjectId());
			map.setProject(project);
		}
		if(map.getId()==null) {
			mapDao.save(map);
		} else {
			mapDao.update(map);
		}
	}

	public void deleteMap(Long mapId) {
		mapDao.delete(mapId);
	}

	
}
