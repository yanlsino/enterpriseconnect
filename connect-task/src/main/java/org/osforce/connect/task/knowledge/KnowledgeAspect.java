package org.osforce.connect.task.knowledge;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
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

	private Task questionViewCountTask;

	public KnowledgeAspect() {
	}
	
	@Autowired
	@Qualifier("questionViewCountTask")
	public void setQuestionViewCountTask(Task questionViewCountTask) {
		this.questionViewCountTask = questionViewCountTask;
	}
	
	@AfterReturning("execution(* org.osforce.connect.service.knowledge.QuestionService.viewQuestion(..))")
	public void viewTopic(JoinPoint jp) {
		Long questionId = (Long) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("questionId", questionId);
		questionViewCountTask.doAsyncTask(context);
	}
}
