package org.osforce.connect.service.system.impl;

import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.ProjectFeatureDao;
import org.osforce.connect.dao.system.RoleDao;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.service.system.ProjectFeatureService;
import org.osforce.platform.dao.support.QueryAppender;
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

	private RoleDao roleDao;
	private ProjectDao projectDao;
	private ProjectFeatureDao projectFeatureDao;
	
	public ProjectFeatureServiceImpl() {
	}
	
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
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
	
	public ProjectFeature getProjectFeature(String code, Long projectId) {
		QueryAppender appender = new QueryAppender()
				.equal("projectFeature.code", code)
				.equal("projectFeature.project.id", projectId);
		return projectFeatureDao.findUnique(appender);
	}

	public void createProjectFeature(ProjectFeature feature) {
		updateProjectFeature(feature);
	}

	public void updateProjectFeature(ProjectFeature feature) {
		if(feature.getRoleId()!=null) {
			Role role = roleDao.get(feature.getRoleId());
			feature.setRole(role);
		}
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
