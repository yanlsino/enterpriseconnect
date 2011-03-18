package org.osforce.e2.dao.discussion.impl;

import org.osforce.e2.dao.discussion.TopicDao;
import org.osforce.e2.entity.discussion.Topic;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:14:19 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class TopicDaoImpl extends AbstractDao<Topic> 
	implements TopicDao {

	public TopicDaoImpl() {
		super(Topic.class);
	}
}
