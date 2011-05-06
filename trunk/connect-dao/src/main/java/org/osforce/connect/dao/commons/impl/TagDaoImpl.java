package org.osforce.connect.dao.commons.impl;

import org.osforce.connect.dao.commons.TagDao;
import org.osforce.connect.entity.commons.Tag;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 9:16:46 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class TagDaoImpl extends AbstractDao<Tag>
	implements TagDao {

	public TagDaoImpl() {
		super(Tag.class);
	}
	
}
