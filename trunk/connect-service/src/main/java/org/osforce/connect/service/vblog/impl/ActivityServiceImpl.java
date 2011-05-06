package org.osforce.connect.service.vblog.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.commons.ActivityDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.vblog.ActivityService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 14, 2011 - 11:37:22 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	private UserDao userDao;
	private ProjectDao projectDao;
	private ActivityDao activityDao;
	
	public ActivityServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public Activity getActivity(Long activityId) {
		return activityDao.get(activityId);
	}

	public void createActivity(Activity activity) {
		updateActivity(activity);
	}

	public void updateActivity(Activity activity) {
		if(activity.getEnteredId()!=null) {
			User enteredBy = userDao.get(activity.getEnteredId());
			activity.setEnteredBy(enteredBy);
		}
		if(activity.getProjectId()!=null) {
			Project project = projectDao.get(activity.getProjectId());
			activity.setProject(project);
		}
		Date now = new Date();
		if(activity.getId()==null) {
			activity.setEntered(now);
			activityDao.save(activity);
		} else {
			activityDao.update(activity);
		}
	}

	public void deleteActivity(Long activityId) {
		activityDao.delete(activityId);
	}

	public Page<Activity> getActivityPage(Page<Activity> page,
			List<String> activityTypes) {
		QueryAppender appender = new QueryAppender();
		appender.in("activity.type", activityTypes)
				.desc("activity.entered");
		return activityDao.findPage(page, appender);
	}
	
	public Page<Activity> getActivityPage(Page<Activity> page, Project project,
			List<String> activityTypes) {
		if(project==null) {
			return getActivityPage(page, activityTypes);
		} else {
			return activityDao.findPage(page, project, activityTypes);
		}
	}
	
}
