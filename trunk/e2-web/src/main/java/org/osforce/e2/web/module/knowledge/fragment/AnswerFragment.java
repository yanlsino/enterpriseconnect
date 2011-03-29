package org.osforce.e2.web.module.knowledge.fragment;

import org.osforce.e2.entity.knowledge.Answer;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.knowledge.AnswerService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 12:18:20 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class AnswerFragment {

	private AnswerService answerService;
	
	public AnswerFragment() {
	}
	
	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
	
	public String doListView(@Param Long questionId,
			Page<Answer> page, FragmentContext context) {
		if(questionId!=null) {
			page = answerService.getAnswerPage(page, questionId);
			context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
			return "knowledge/answers_list";
		}
		return "commons/blank";
	}
	
	public String doFormView(@Param Long questionId, 
			Project project, User user, FragmentContext context) {
		Answer answer = new Answer();
		answer.setQuestionId(questionId);
		answer.setEnteredBy(user);
		answer.setModifiedBy(user);
		context.putRequestData(AttributeKeys.ANSWER_KEY_READABLE, answer);
		return "knowledge/answer_form";
	}
}
