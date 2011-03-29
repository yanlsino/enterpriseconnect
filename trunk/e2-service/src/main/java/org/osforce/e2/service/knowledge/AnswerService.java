package org.osforce.e2.service.knowledge;

import org.osforce.e2.entity.knowledge.Answer;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:50:07 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface AnswerService {

	Answer getAnswer(Long answerId);
	
	void createAnswer(Answer answer);
	
	void updateAnswer(Answer answer);
	
	void deleteAnswer(Long answerId);

	Page<Answer> getAnswerPage(Page<Answer> page, Long questionId);
}
