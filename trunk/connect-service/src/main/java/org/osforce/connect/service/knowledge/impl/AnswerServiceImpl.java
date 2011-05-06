package org.osforce.connect.service.knowledge.impl;

import java.util.Date;

import org.osforce.connect.dao.knowledge.AnswerDao;
import org.osforce.connect.dao.knowledge.QuestionDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.knowledge.AnswerService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:53:36 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

	private UserDao userDao;
	private AnswerDao answerDao;
	private QuestionDao questionDao;
	
	public AnswerServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
	
	@Autowired
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public Answer getAnswer(Long answerId) {
		return answerDao.get(answerId);
	}

	public void createAnswer(Answer answer) {
		updateAnswer(answer);
	}

	public void updateAnswer(Answer answer) {
		if(answer.getEnteredId()!=null) {
			User enteredBy = userDao.get(answer.getEnteredId());
			answer.setEnteredBy(enteredBy);
		}
		if(answer.getModifiedId()!=null) {
			User modifiedBy = userDao.get(answer.getModifiedId());
			answer.setModifiedBy(modifiedBy);
		}
		if(answer.getQuestionId()!=null) {
			Question question = questionDao.get(answer.getQuestionId());
			answer.setQuestion(question);
		}
		Date now = new Date();
		answer.setModified(now);
		if(answer.getId()==null) {
			answer.setEntered(now);
			answerDao.save(answer);
		} else {
			answerDao.update(answer);
		}
	}

	public void deleteAnswer(Long answerId) {
		answerDao.delete(answerId);
	}
	
	public Page<Answer> getAnswerPage(Page<Answer> page, Long questionId) {
		QueryAppender appender = new QueryAppender()
				.equal("answer.question.id", questionId)
				.desc("answer.modified");
		return answerDao.findPage(page, appender);
	}
	
}
