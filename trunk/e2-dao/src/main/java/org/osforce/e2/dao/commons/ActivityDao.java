package org.osforce.e2.dao.commons;

import java.util.List;

import org.osforce.e2.entity.commons.Activity;
import org.osforce.e2.entity.system.Project;
import org.osforce.platform.dao.BaseDao;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:53:07 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ActivityDao extends BaseDao<Activity> {

	Page<Activity> findPage(Page<Activity> page, Project project,
			List<String> activityTypes);

}
