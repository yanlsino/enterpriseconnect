package org.osforce.e2.service.knowledge;

import org.osforce.e2.entity.knowledge.Question;
import org.osforce.e2.entity.system.Project;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:50:12 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface QuestionService {

	Question getQuestion(Long questionId);
	
	void createQuestion(Question question);
	
	void updateQuestion(Question question);
	
	void deleteQuestion(Long questionId);

	Page<Question> getQuestionPage(Page<Question> page);

	Page<Question> getQuestionPage(Page<Question> page, Project project);

	/**
	 * Intercept by @see KnowledgeAspect
	 * @param questionId
	 * @return
	 */
	Question viewQuestion(Long questionId);
}
