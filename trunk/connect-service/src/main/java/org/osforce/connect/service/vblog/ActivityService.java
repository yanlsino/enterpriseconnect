package org.osforce.connect.service.vblog;

import java.util.List;

import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.system.Project;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 14, 2011 - 11:38:07 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ActivityService {

	Activity getActivity(Long activityId);
	
	void createActivity(Activity activity);
	
	void updateActivity(Activity activity);
	
	void deleteActivity(Long activityId);

	Page<Activity> getActivityPage(Page<Activity> page, List<String> activityTypes);

	Page<Activity> getActivityPage(Page<Activity> page, Project project,
			List<String> asList);
}
