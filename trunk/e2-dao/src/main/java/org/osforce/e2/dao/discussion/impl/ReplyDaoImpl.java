package org.osforce.e2.dao.discussion.impl;

import org.osforce.e2.dao.discussion.ReplyDao;
import org.osforce.e2.entity.discussion.Reply;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:12:21 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ReplyDaoImpl extends AbstractDao<Reply> 
	implements ReplyDao {

	public ReplyDaoImpl() {
		super(Reply.class);
	}
}
