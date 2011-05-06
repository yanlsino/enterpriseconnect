package org.osforce.connect.task.knowledge;

import java.util.Map;

import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.service.commons.StatisticService;
import org.osforce.connect.service.knowledge.QuestionService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 29, 2011 - 12:37:01 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class QuestionViewCountTask extends AbstractTask {

	private StatisticService statisticService;
	private QuestionService questionService;
	
	public QuestionViewCountTask() {
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long questionId = (Long) context.get("questionId");
		Question question = questionService.getQuestion(questionId);
		Statistic statistic = statisticService.getStatistic(questionId, Question.NAME);
		if(statistic==null) {
			statistic = new Statistic(questionId, Question.NAME);
		}
		statistic.countAdd();
		statistic.setProjectId(question.getProjectId());
		statisticService.createStatistic(statistic);
	}

}
