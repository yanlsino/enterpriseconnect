package org.osforce.connect.task.discussion;

import java.util.Map;

import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.connect.service.discussion.ForumService;
import org.osforce.connect.service.vblog.ActivityService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 3:44:52 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ForumActivityStreamTask extends AbstractTask {

	private ForumService forumService;
	private ActivityService activityService;

	public ForumActivityStreamTask() {
	}

	@Autowired
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long forumId = (Long) context.get("forumId");
		Forum forum = forumService.getForum(forumId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(forumId);
		activity.setEntity(Forum.NAME);
		activity.setType(Forum.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(forum.getProjectId());
		activity.setEnteredId(forum.getModifiedId());
		activityService.createActivity(activity);
	}

}
