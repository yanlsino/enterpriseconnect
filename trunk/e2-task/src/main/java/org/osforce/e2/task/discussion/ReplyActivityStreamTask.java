package org.osforce.e2.task.discussion;

import java.util.Map;

import org.osforce.e2.entity.commons.Activity;
import org.osforce.e2.entity.discussion.Reply;
import org.osforce.e2.service.discussion.ReplyService;
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
 * @create Mar 2, 2011 - 5:22:37 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ReplyActivityStreamTask extends AbstractTask {

	private ReplyService replyService;
	private Configuration configuration;
	private ActivityService activityService;
	
	public ReplyActivityStreamTask() {
	}
	
	@Autowired
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
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
		Long replyId = (Long) context.get("replyId");
		Reply reply = replyService.getReply(replyId);
		context.put("reply", reply);
		context.put("site", reply.getTopic().getForum().getProject().getCategory().getSite());
		String template = (String) context.get("template");
		String description = FreemarkerUtil.render(configuration, template, context);
		Activity activity = new Activity();
		activity.setLinkedId(replyId);
		activity.setEntity(Reply.NAME);
		activity.setType(Reply.NAME);
		activity.setDescription(description);
		activity.setProjectId(reply.getTopic().getForum().getProjectId());
		activity.setEnteredId(reply.getModifiedId());
		activityService.createActivity(activity);
	}

}
