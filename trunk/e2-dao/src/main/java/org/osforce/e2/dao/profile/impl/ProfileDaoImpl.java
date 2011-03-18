package org.osforce.e2.dao.profile.impl;

import org.osforce.e2.dao.profile.ProfileDao;
import org.osforce.e2.entity.profile.Profile;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.User;
import org.osforce.platform.dao.support.AbstractDao;
import org.osforce.platform.dao.support.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 6:50:41 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ProfileDaoImpl extends AbstractDao<Profile> 
	implements ProfileDao {

	public ProfileDaoImpl() {
		super(Profile.class);
	}
	
	static final String FIND_CONCERED_PAGE_JPQL = "FROM Profile p WHERE p.project.category.id = ?1 AND p.id IN (SELECT l.toId FROM Link l WHERE l.from.id = ?2 AND l.entity = ?3)";
	public Page<Profile> findConcernedPage(Page<Profile> page,
			ProjectCategory category, User user) {
		return findPage(page, FIND_CONCERED_PAGE_JPQL,
				category.getId(), user.getProjectId(), Profile.NAME);
	}
	
}
