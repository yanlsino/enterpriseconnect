package org.osforce.connect.task.knowledge;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.connect.entity.knowledge.Question;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Mar 29, 2011 - 12:36:05 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class KnowledgeAspect {

	private static final String TEMPLATE_QUESTION_UPDATE = "activity/question_update.ftl";
	private static final String TEMPLATE_ANSWER_UPDATE = "activity/answer_update.ftl";

	private Task questionViewCountTask;
	private Task questionActivityStreamTask;
	private Task answerActivityStreamTask;

	public KnowledgeAspect() {
	}

	@Autowired
	@Qualifier("questionViewCountTask")
	public void setQuestionViewCountTask(Task questionViewCountTask) {
		this.questionViewCountTask = questionViewCountTask;
	}

	@Autowired
	@Qualifier("questionActivityStreamTask")
	public void setQuestionActivityStreamTask(Task questionActivityStreamTask) {
		this.questionActivityStreamTask = questionActivityStreamTask;
	}

	@Autowired
	@Qualifier("answerActivityStreamTask")
	public void setAnswerActivityStreamTask(Task answerActivityStreamTask) {
		this.answerActivityStreamTask = answerActivityStreamTask;
	}

	@AfterReturning("execution(* org.osforce.connect.service.knowledge.QuestionService.viewQuestion(..))")
	public void viewQuestion(JoinPoint jp) {
		Long questionId = (Long) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("questionId", questionId);
		questionViewCountTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.knowledge.QuestionService.createQuestion(..)) ||"
			+ "execution(* org.osforce.connect.service.knowledge.QuestionService.updateQuestion(..))")
	public void updateQuestion(JoinPoint jp) {
		Question question = (Question) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("questionId", question.getId());
		context.put("template", TEMPLATE_QUESTION_UPDATE);
		questionActivityStreamTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.knowledge.AnswerService.createAnswer(..)) ||"
			+ "execution(* org.osforce.connect.service.knowledge.AnswerService.updateAnswer(..))")
	public void updateAnswer(JoinPoint jp) {
		Answer answer = (Answer) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("answerId", answer.getId());
		context.put("template", TEMPLATE_ANSWER_UPDATE);
		answerActivityStreamTask.doAsyncTask(context);
	}

}
