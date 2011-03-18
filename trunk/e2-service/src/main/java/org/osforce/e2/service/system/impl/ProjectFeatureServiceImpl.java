package org.osforce.e2.service.system.impl;

import org.osforce.e2.dao.system.ProjectDao;
import org.osforce.e2.dao.system.ProjectFeatureDao;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectFeature;
import org.osforce.e2.service.system.ProjectFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:23:56 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ProjectFeatureServiceImpl implements ProjectFeatureService {

	private ProjectDao projectDao;
	private ProjectFeatureDao projectFeatureDao;
	
	public ProjectFeatureServiceImpl() {
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setProjectFeatureDao(ProjectFeatureDao projectFeatureDao) {
		this.projectFeatureDao = projectFeatureDao;
	}

	public ProjectFeature getProjectFeature(Long featureId) {
		return projectFeatureDao.get(featureId);
	}

	public void createProjectFeature(ProjectFeature feature) {
		updateProjectFeature(feature);
	}

	public void updateProjectFeature(ProjectFeature feature) {
		if(feature.getProjectId()!=null) {
			Project project = projectDao.get(feature.getProjectId());
			feature.setProject(project);
		}
		if(feature.getId()==null) {
			projectFeatureDao.save(feature);
		} else {
			projectFeatureDao.update(feature);
		}
	}

	public void deleteProjectFeature(Long featureId) {
		// TODO Auto-generated method stub
		
	}
}
