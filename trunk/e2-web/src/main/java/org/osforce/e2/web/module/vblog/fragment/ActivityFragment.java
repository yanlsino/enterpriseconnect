package org.osforce.e2.web.module.vblog.fragment;

import java.util.Arrays;

import org.osforce.e2.entity.commons.Activity;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.Site;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.commons.CommentService;
import org.osforce.e2.service.system.UserService;
import org.osforce.e2.service.vblog.ActivityService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 14, 2011 - 11:34:30 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ActivityFragment {

	private UserService userService;
	private ActivityService activityService;
	private CommentService commentService;
	
	public ActivityFragment() {
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public String doListView(@Pref String[] activityTypes, Page<Activity> page,  
			Project project, Site site, FragmentContext context) {
		page = activityService.getActivityPage(page, project, Arrays.asList(activityTypes));
		for(Activity activity : page.getResult()) {
			Long count = commentService.countComment(activity.getId(), Activity.NAME);
			activity.setCommentCount(count);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "vblog/activities_list";
	}
	
	public String doFormView(@Pref String activityType, User user, 
			Project project, FragmentContext context) {
		if(user!=null) {
			user = userService.getUser(user.getId());
			Activity activity = new Activity();
			activity.setType(activityType);
			activity.setEnteredBy(user);
			activity.setProject(project);
			context.putRequestData(AttributeKeys.ACTIVITY_KEY_READABLE, activity);
			return "vblog/activity_form";
		}
		return "commons/blank";
	}
	
}
