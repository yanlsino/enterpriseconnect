package org.osforce.connect.service.knowledge.impl;

import java.util.Date;

import org.osforce.connect.dao.knowledge.QuestionDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.knowledge.QuestionService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:52:36 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	private UserDao userDao;
	private ProjectDao projectDao;
	private QuestionDao questionDao;
	
	public QuestionServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public Question getQuestion(Long questionId) {
		return questionDao.get(questionId);
	}
	
	public Question viewQuestion(Long questionId) {
		return questionDao.get(questionId);
	}

	public void createQuestion(Question question) {
		updateQuestion(question);
	}

	public void updateQuestion(Question question) {
		if(question.getEnteredId()!=null) {
			User enteredBy = userDao.get(question.getEnteredId());
			question.setEnteredBy(enteredBy);
		}
		if(question.getModifiedId()!=null) {
			User modifiedBy = userDao.get(question.getModifiedId());
			question.setModifiedBy(modifiedBy);
		}
		if(question.getProjectId()!=null) {
			Project project = projectDao.get(question.getProjectId());
			question.setProject(project);
		}
		Date now = new Date();
		question.setModified(now);
		if(question.getId()==null) {
			question.setEntered(now);
			questionDao.save(question);
		} else {
			questionDao.update(question);
		}
	}

	public void deleteQuestion(Long questionId) {
		questionDao.delete(questionId);
	}
	
	public Page<Question> getQuestionPage(Page<Question> page) {
		QueryAppender appender = new QueryAppender();
		return questionDao.findPage(page, appender);
	}
	
	public Page<Question> getQuestionPage(Page<Question> page, Project project) {
		QueryAppender appender = new QueryAppender()
				.equal("question.project.id", project.getId())
				.desc("question.modified");
		return questionDao.findPage(page, appender);
	}
	
}
