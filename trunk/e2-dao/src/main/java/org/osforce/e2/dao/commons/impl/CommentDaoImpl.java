package org.osforce.e2.dao.commons.impl;

import org.osforce.e2.dao.commons.CommentDao;
import org.osforce.e2.entity.commons.Comment;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:58:33 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class CommentDaoImpl extends AbstractDao<Comment> 
	implements CommentDao {

	public CommentDaoImpl() {
		super(Comment.class);
	}
}
