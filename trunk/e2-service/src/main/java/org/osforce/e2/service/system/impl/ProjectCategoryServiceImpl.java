package org.osforce.e2.service.system.impl;

import java.util.List;

import org.osforce.e2.dao.system.ProjectCategoryDao;
import org.osforce.e2.dao.system.SiteDao;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.Site;
import org.osforce.e2.service.system.ProjectCategoryService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 12:41:02 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ProjectCategoryServiceImpl implements ProjectCategoryService {

	private SiteDao siteDao;
	private ProjectCategoryDao projectCategoryDao;
	
	public ProjectCategoryServiceImpl() {
	}
	
	@Autowired
	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}
	
	@Autowired
	public void setProjectCategoryDao(ProjectCategoryDao projectCategoryDao) {
		this.projectCategoryDao = projectCategoryDao;
	}
	
	public ProjectCategory getProjectCategory(Long categoryId) {
		return projectCategoryDao.get(categoryId);
	}
	
	public ProjectCategory getProjectCategory(Site site, String categoryCode) {
		QueryAppender appender = new QueryAppender();
		appender.equal("projectCategory.site.id", site.getId())
				.equal("projectCategory.code", categoryCode);
		return projectCategoryDao.findUnique(appender);
	}

	public void createProjectCategory(ProjectCategory projectCategory) {
		updateProjectCategory(projectCategory);
	}

	public void updateProjectCategory(ProjectCategory category) {
		if(category.getSiteId()!=null) {
			Site site = siteDao.get(category.getSiteId());
			category.setSite(site);
		}
		if(category.getParentId()!=null) {
			ProjectCategory parent = projectCategoryDao.get(category.getParentId());
			category.setParent(parent);
		}
		if(category.getId()==null) {
			projectCategoryDao.save(category);
		} else {
			projectCategoryDao.update(category);
		}
	}

	public void deleteProjectCategory(Long categoryId) {
		projectCategoryDao.delete(categoryId);
	}
	
	public List<ProjectCategory> getProjectCategoryList(Long siteId) {
		return projectCategoryDao.find(siteId);
	}
	
	public List<ProjectCategory> getProjectCategoryList(Long siteId,
			Long parentId) {
		if(parentId==null) {
			return projectCategoryDao.find(siteId);
		} else {
			QueryAppender appender = new QueryAppender();
			appender.equal("projectCategory.site.id", siteId)
					.equal("projectCategory.parent.id", parentId);
			return projectCategoryDao.find(appender);
		}
	}
	
	public List<ProjectCategory> getSiblingProjectCategoryList(Long siteId,
			Long categoryId) {
		ProjectCategory category = projectCategoryDao.get(categoryId);
		if(category.getParent()==null) {
			return projectCategoryDao.find(siteId);
		} else {
			return category.getParent().getChildren();
		}
	}
	
	public Page<ProjectCategory> getProjectCategoryPage(Page<ProjectCategory> page) {
		return projectCategoryDao.findPage(page, new QueryAppender());
	}
	
	public Page<ProjectCategory> getProjectCategoryPage(Page<ProjectCategory> page, Long siteId) {
		return projectCategoryDao.findPage(page, siteId);
	}
	
	public Page<ProjectCategory> getProjectCategoryPage(
			Page<ProjectCategory> page, Long siteId, Long parentId) {
		if(parentId==null) {
			return projectCategoryDao.findPage(page, siteId);
		} else {
			QueryAppender appender = new QueryAppender();
			appender.equal("projectCategory.site.id", siteId)
					.equal("projectCategory.parent.id", parentId)
					.asc("projectCategory.code");
			return projectCategoryDao.findPage(page, appender);
		}
	}
	
}
