package org.osforce.connect.dao.system;

import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.User;
import org.osforce.platform.dao.BaseDao;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:02:28 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProjectDao extends BaseDao<Project> {

	/*Page<Project> findConcernedPage(Page<Project> page,
			ProjectCategory category, User user);*/

}
