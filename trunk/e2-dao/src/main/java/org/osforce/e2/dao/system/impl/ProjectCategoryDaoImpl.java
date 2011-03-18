package org.osforce.e2.dao.system.impl;

import java.util.List;

import org.osforce.e2.dao.system.ProjectCategoryDao;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.Site;
import org.osforce.platform.dao.support.AbstractDao;
import org.osforce.platform.dao.support.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:04:44 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ProjectCategoryDaoImpl extends AbstractDao<ProjectCategory>
	implements ProjectCategoryDao {

	public ProjectCategoryDaoImpl() {
		super(ProjectCategory.class);
	}

	static final String FIND_JPQL = "FROM ProjectCategory pc WHERE pc.site.id = ?1 AND pc.parent IS NULL ORDER BY pc.level";
	public List<ProjectCategory> find(Long siteId) {
		return find(FIND_JPQL, siteId);
	}
	
	static final String FIND_PAGE_JPQL = "FROM ProjectCategory pc WHERE pc.site.id = ?1 AND pc.parent IS NULL ORDER BY pc.level";
	public Page<ProjectCategory> findPage(Page<ProjectCategory> page,
			Long siteId) {
		return findPage(page, FIND_PAGE_JPQL, siteId);
	}
	
}
