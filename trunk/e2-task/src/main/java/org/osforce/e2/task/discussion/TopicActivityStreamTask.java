package org.osforce.e2.task.discussion;

import java.util.Map;

import org.osforce.e2.entity.commons.Activity;
import org.osforce.e2.entity.discussion.Topic;
import org.osforce.e2.service.discussion.TopicService;
import org.osforce.e2.service.vblog.ActivityService;
import org.osforce.e2.task.support.FreemarkerUtil;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 5:07:29 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class TopicActivityStreamTask extends AbstractTask {

	private TopicService topicService;
	private Configuration configuration;
	private ActivityService activityService;
	
	public TopicActivityStreamTask() {
	}
	
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long topicId = (Long) context.get("topicId");
		Topic topic = topicService.getTopic(topicId);
		context.put("topic", topic);
		context.put("site", topic.getForum().getProject().getCategory().getSite());
		String template = (String) context.get("template");
		String description = FreemarkerUtil.render(configuration, template, context);
		Activity activity = new Activity();
		activity.setLinkedId(topicId);
		activity.setEntity(Topic.NAME);
		activity.setType(Topic.NAME);
		activity.setDescription(description);
		activity.setProjectId(topic.getForum().getProjectId());
		activity.setEnteredId(topic.getModifiedId());
		activityService.createActivity(activity);
	}
}
