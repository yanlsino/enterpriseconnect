package org.osforce.connect.dao.profile;

import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.User;
import org.osforce.platform.dao.BaseDao;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 6:50:36 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProfileDao extends BaseDao<Profile> {

	Page<Profile> findConcernedPage(Page<Profile> page,
			ProjectCategory category, User user);

}
