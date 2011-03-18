package org.osforce.e2.dao.blog.impl;

import org.osforce.e2.dao.blog.BlogCategoryDao;
import org.osforce.e2.entity.blog.BlogCategory;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:02:56 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class BlogCategoryDaoImpl extends AbstractDao<BlogCategory> 
	implements BlogCategoryDao {

	public BlogCategoryDaoImpl() {
		super(BlogCategory.class);
	}
}
