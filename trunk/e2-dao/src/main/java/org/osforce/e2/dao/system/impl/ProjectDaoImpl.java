package org.osforce.e2.dao.system.impl;

import org.osforce.e2.dao.system.ProjectDao;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.User;
import org.osforce.platform.dao.support.AbstractDao;
import org.osforce.platform.dao.support.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:03:21 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ProjectDaoImpl extends AbstractDao<Project> 
	implements ProjectDao {

	public ProjectDaoImpl() {
		super(Project.class);
	}
	
	/*static final String FIND_CONCERED_PAGE_JPQL = "FROM Project p WHERE p.category.id = ?1 AND p.id IN (SELECT l.toId FROM Link l WHERE l.from.id = ?2 AND l.entity = ?3)";
	public Page<Project> findConcernedPage(Page<Project> page,
			ProjectCategory category, User user) {
		return findPage(page, FIND_CONCERED_PAGE_JPQL, 
				category.getId(), user.getProjectId(), Project.class.getSimpleName());
	}*/
}
