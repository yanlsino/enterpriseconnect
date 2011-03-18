package org.osforce.e2.service.system;

import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.User;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 12:13:49 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProjectService {

	Project getProject(Long projectId);
	
	Project getProject(String uniqueId);
	
	void createProject(Project project);
	
	void updateProject(Project project);
	
	void deleteProject(Long projectId);
	
	Page<Project> getProjectPage(Page<Project> page);

	Page<Project> getProjectPage(Page<Project> page, ProjectCategory category);

	/*Page<Project> getConcernedProjectPage(Page<Project> page,
			ProjectCategory category, User user);*/

}
