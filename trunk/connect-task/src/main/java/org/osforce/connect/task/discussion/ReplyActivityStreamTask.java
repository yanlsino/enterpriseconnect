package org.osforce.connect.task.discussion;

import java.util.Map;

import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.discussion.Reply;
import org.osforce.connect.service.discussion.ReplyService;
import org.osforce.connect.service.vblog.ActivityService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	private ActivityService activityService;

	public ReplyActivityStreamTask() {
	}

	@Autowired
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long replyId = (Long) context.get("replyId");
		Reply reply = replyService.getReply(replyId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(replyId);
		activity.setEntity(Reply.NAME);
		activity.setType(Reply.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(reply.getTopic().getForum().getProjectId());
		activity.setEnteredId(reply.getModifiedId());
		activityService.createActivity(activity);
	}

}
