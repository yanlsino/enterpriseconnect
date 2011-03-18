package org.osforce.e2.service.system;

import java.util.List;

import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.Site;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 12:09:53 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProjectCategoryService {

	ProjectCategory getProjectCategory(Long categoryId);
	
	ProjectCategory getProjectCategory(Site site, String categoryCode);
	
	void createProjectCategory(ProjectCategory category);
	
	void updateProjectCategory(ProjectCategory category);
	
	void deleteProjectCategory(Long categoryId);

	Page<ProjectCategory> getProjectCategoryPage(Page<ProjectCategory> page);
	
	Page<ProjectCategory> getProjectCategoryPage(Page<ProjectCategory> page, Long siteId);
	
	Page<ProjectCategory> getProjectCategoryPage(Page<ProjectCategory> page,
			Long siteId, Long parentId);
	
	List<ProjectCategory> getProjectCategoryList(Long siteId);

	List<ProjectCategory> getProjectCategoryList(Long siteId, Long parentId);

	List<ProjectCategory> getSiblingProjectCategoryList(Long siteId,
			Long categoryId);

}
