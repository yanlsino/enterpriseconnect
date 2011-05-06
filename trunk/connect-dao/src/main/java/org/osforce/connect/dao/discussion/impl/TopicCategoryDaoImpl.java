package org.osforce.connect.dao.discussion.impl;

import org.osforce.connect.dao.discussion.TopicCategoryDao;
import org.osforce.connect.entity.discussion.TopicCategory;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:13:13 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class TopicCategoryDaoImpl extends AbstractDao<TopicCategory>
	implements TopicCategoryDao {
	
	public TopicCategoryDaoImpl() {
		super(TopicCategory.class);
	}
}
