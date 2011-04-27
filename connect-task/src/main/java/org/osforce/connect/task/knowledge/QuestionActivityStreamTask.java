package org.osforce.connect.task.knowledge;

import java.util.Map;

import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.service.knowledge.QuestionService;
import org.osforce.connect.service.vblog.ActivityService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 1:21:55 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class QuestionActivityStreamTask extends AbstractTask {

	private ActivityService activityService;
	private QuestionService questionService;

	public QuestionActivityStreamTask() {
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long questionId = (Long) context.get("questionId");
		Question question = questionService.getQuestion(questionId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(questionId);
		activity.setEntity(Question.NAME);
		activity.setType(Question.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(question.getProjectId());
		activity.setEnteredId(question.getModifiedId());
		activityService.createActivity(activity);
	}

}
