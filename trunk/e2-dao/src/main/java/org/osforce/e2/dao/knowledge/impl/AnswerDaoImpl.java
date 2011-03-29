package org.osforce.e2.dao.knowledge.impl;

import org.osforce.e2.dao.knowledge.AnswerDao;
import org.osforce.e2.entity.knowledge.Answer;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:31:48 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class AnswerDaoImpl extends AbstractDao<Answer> 
	implements AnswerDao {

	public AnswerDaoImpl() {
		super(Answer.class);
	}
	
}
