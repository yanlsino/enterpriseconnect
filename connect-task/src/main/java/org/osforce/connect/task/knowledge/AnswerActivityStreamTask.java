package org.osforce.connect.task.knowledge;

import java.util.Map;

import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.connect.service.knowledge.AnswerService;
import org.osforce.connect.service.vblog.ActivityService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 1:31:26 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class AnswerActivityStreamTask extends AbstractTask {

	private AnswerService answerService;
	private ActivityService activityService;

	public AnswerActivityStreamTask() {
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long answerId = (Long) context.get("answerId");
		Answer answer = answerService.getAnswer(answerId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(answerId);
		activity.setEntity(Answer.NAME);
		activity.setType(Answer.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(answer.getQuestion().getProjectId());
		activity.setEnteredId(answer.getModifiedId());
		activityService.createActivity(activity);
	}

}
