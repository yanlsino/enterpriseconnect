package org.osforce.connect.service.system.impl;

import java.util.Date;

import org.osforce.connect.dao.system.ProjectCategoryDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 12:43:42 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	private UserDao userDao;
	private ProjectDao projectDao;
	private ProjectCategoryDao projectCategoryDao;
	
	public ProjectServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Autowired
	public void setProjectCategoryDao(ProjectCategoryDao projectCategoryDao) {
		this.projectCategoryDao = projectCategoryDao;
	}
	
	public Project getProject(Long projectId) {
		return projectDao.get(projectId);
	}

	public void createProject(Project project) {
		updateProject(project);
	}

	public void updateProject(Project project) {
		if(project.getCategoryId()!=null) {
			ProjectCategory category = projectCategoryDao.get(project.getCategoryId());
			project.setCategory(category);
		}
		if(project.getSubCategoryId1()!=null) {
			ProjectCategory subCategory1 = projectCategoryDao
					.get(project.getSubCategoryId1());
			project.setSubCategory1(subCategory1);
		}
		if(project.getSubCategoryId2()!=null) {
			ProjectCategory subCategory2 = projectCategoryDao
					.get(project.getSubCategoryId2());
			project.setSubCategory2(subCategory2);
		}
		if(project.getSubCategoryId3()!=null) {
			ProjectCategory subCategory3 = projectCategoryDao
					.get(project.getSubCategoryId3());
			project.setSubCategory3(subCategory3);
		}
		if(project.getEnteredId()!=null) {
			User enteredBy = userDao.get(project.getEnteredId()) ;
			project.setEnteredBy(enteredBy);
		} 
		if(project.getModifiedId()!=null) {
			User modifiedBy = userDao.get(project.getModifiedId());
			project.setModifiedBy(modifiedBy);
		}
		Date now = new Date();
		project.setModified(now);
		if(project.getId()==null) {
			project.setEntered(now);
			projectDao.save(project);
		} else {
			projectDao.update(project);
		}
	}

	public void deleteProject(Long projectId) {
		projectDao.delete(projectId);
	}

	public Page<Project> getProjectPage(Page<Project> page) {
		return projectDao.findPage(page, new QueryAppender());
	}

	public Project getProject(String uniqueId) {
		QueryAppender appender = new QueryAppender();
		appender.equal("project.uniqueId", uniqueId);
		return projectDao.findUnique(appender);
	}
	
	public Page<Project> getProjectPage(Page<Project> page,
			ProjectCategory category) {
		QueryAppender appender = new QueryAppender();
		appender.equal("project.category.id", category.getId()).desc("project.entered");
		return projectDao.findPage(page, appender);
	}
	
	/*public Page<Project> getConcernedProjectPage(Page<Project> page,
			ProjectCategory category, User user) {
		return projectDao.findConcernedPage(page, category, user);
	}*/

}
