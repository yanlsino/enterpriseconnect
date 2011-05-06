package org.osforce.connect.dao.discussion.impl;

import org.osforce.connect.dao.discussion.ForumDao;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:11:25 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ForumDaoImpl extends AbstractDao<Forum> 
	implements ForumDao {

	public ForumDaoImpl() {
		super(Forum.class);
	}
}
