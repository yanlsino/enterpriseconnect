package org.osforce.e2.dao.knowledge.impl;

import org.osforce.e2.dao.knowledge.QuestionDao;
import org.osforce.e2.entity.knowledge.Question;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:29:47 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class QuestionDaoImpl extends AbstractDao<Question>
	implements QuestionDao {

	public QuestionDaoImpl() {
		super(Question.class);
	}
	
}
