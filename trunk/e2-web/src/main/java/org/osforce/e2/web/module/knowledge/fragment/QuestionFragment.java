package org.osforce.e2.web.module.knowledge.fragment;

import org.osforce.e2.entity.commons.Statistic;
import org.osforce.e2.entity.knowledge.Question;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.commons.StatisticService;
import org.osforce.e2.service.knowledge.QuestionService;
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
 * @create Mar 24, 2011 - 12:17:50 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class QuestionFragment {

	private StatisticService statisticService;
	private QuestionService questionService;
	
	public QuestionFragment() {
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public String doRecentView(Page<Question> page, 
			Project project, FragmentContext context) {
		page = questionService.getQuestionPage(page, project);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "knowledge/questions_recent";
	}
	
	public String doListView(Page<Question> page, 
			Project project, FragmentContext context) {
		page = questionService.getQuestionPage(page, project);
		for(Question question : page.getResult()) {
			Statistic statistic = statisticService.getStatistic(question.getId(), Question.NAME);
			question.setViews(statistic!=null ? statistic.getCount() : 0);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "knowledge/questions_list";
	}
	
	public String doQuestionView(@Param Long questionId, 
			User user, FragmentContext context) {
		if(questionId!=null) {
			Question question = questionService.viewQuestion(questionId);
			context.putRequestData(AttributeKeys.QUESTION_KEY_READABLE, question);
			return "knowledge/question";
		}
		return "commons/blank";
	}
	
	public String doFormView(@Param Long questionId, 
			Project project, User user, FragmentContext context) {
		Question question = new Question();
		question.setEnteredBy(user);
		question.setModifiedBy(user);
		question.setProject(project);
		if(questionId!=null) {
			question = questionService.getQuestion(questionId);
		}
		context.putRequestData(AttributeKeys.QUESTION_KEY_READABLE, question);
		return "knowledge/question_form";
	}
	
}
